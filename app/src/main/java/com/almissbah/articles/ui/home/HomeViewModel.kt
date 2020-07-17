package com.almissbah.articles.ui.home

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almissbah.articles.data.Resource
import com.almissbah.articles.data.remote.CallbackWrapper
import com.almissbah.articles.data.remote.model.Article
import com.almissbah.articles.data.remote.model.PopularArticlesApiResponse
import com.almissbah.articles.data.remote.model.PopularArticlesRequest
import com.almissbah.articles.data.repo.PopularArticlesRepository
import com.almissbah.articles.data.repo.RepoCallback
import com.almissbah.articles.ui.details.ArticleDetailsFragment
import com.almissbah.articles.utils.ArticlesUtils
import com.almissbah.articles.utils.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val popularArticlesRepository: PopularArticlesRepository) :
    ViewModel() {
    private var mLastestRequest: PopularArticlesRequest? = null
    val tag = "HomeViewModel"

    enum class Action { SHOW_LOADING, SHOW_ARTICLES_LIST, SHOW_CONNECTION_ERROR, SHOW_AUTH_ERROR }

    private var disposable: Disposable? = null
    private val _articles = MutableLiveData<Resource<List<Article>, Action>>()
    val articles: LiveData<Resource<List<Article>, Action>> = _articles


    fun getPopularArticlesForLast7Days() {
        getPopularArticles(PopularArticlesRequest(period = PopularArticlesRequest.ArticlesPeriod.SEVEN_DAYS))
    }

    fun getPopularArticles(request: PopularArticlesRequest) {
        mLastestRequest = request
        showLoading()
        fetchArticles(request, object : RepoCallback<PopularArticlesApiResponse> {
            override fun onResult(result: Resource<PopularArticlesApiResponse, Resource.Status>) {
                when (result.action) {
                    Resource.Status.SUCCESS -> {
                        Log.i(
                            tag,
                            "Resource.Status.SUCCESS : list size =${result.payload!!.articles.size}"
                        )
                        _articles.postValue(
                            Resource(
                                result.payload!!.articles,
                                Action.SHOW_ARTICLES_LIST,
                                ""
                            )
                        )
                    }
                    Resource.Status.CONNECTION_ERROR, Resource.Status.FAIL,
                    Resource.Status.SERVER_ERROR, Resource.Status.NOT_FOUND -> {
                        Log.e(tag, "CONNECTION_ERROR")
                        _articles.postValue(
                            Resource(
                                null,
                                Action.SHOW_CONNECTION_ERROR,
                                "Connection failed, Please check your internet and try again."
                            )
                        )
                    }
                    Resource.Status.FORBIDDEN -> {
                        Log.e(tag, "FORBIDDEN")
                        _articles.postValue(
                            Resource(
                                null,
                                Action.SHOW_AUTH_ERROR,
                                "Authentication failed."
                            )
                        )
                    }
                }
            }
        })
    }

    private fun showLoading() {
        Log.i(tag, "Showing loading")
        _articles.postValue(
            Resource(
                null,
                Action.SHOW_LOADING,
                ""
            )
        )
    }

    private fun fetchArticles(
        request: PopularArticlesRequest,
        callback: RepoCallback<PopularArticlesApiResponse>
    ) {
        popularArticlesRepository.getPopularArticles(request).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(CallbackWrapper(object :
                CallbackWrapper.HttpHandler<PopularArticlesApiResponse>(callback) {
                override fun onSuccess(t: PopularArticlesApiResponse?) {
                    callback.onResult(Resource(t, Resource.Status.SUCCESS, ""))
                }

                override fun onCreated(t: PopularArticlesApiResponse?) {
                }

            }))

    }

    fun unSubscribe() {

    }

    fun retry() {
        getPopularArticles(mLastestRequest ?: PopularArticlesRequest())
    }

    fun getNavigationBundle(article: Article): Bundle {
        return bundleOf(
            ArticleDetailsFragment.ARTICLE_ARG_NAME to ArticlesUtils.convertToJsonString(
                article
            )
        )
    }

}
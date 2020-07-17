package com.almissbah.articles.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.almissbah.articles.R
import com.almissbah.articles.data.Resource
import com.almissbah.articles.data.remote.model.Article
import com.almissbah.articles.ui.base.ArticlesFragment
import com.almissbah.articles.ui.base.HasLoading
import com.almissbah.articles.ui.details.ArticleDetailsFragment
import com.almissbah.articles.ui.home.adapter.ArticlesAdapter
import com.almissbah.articles.utils.hide
import com.almissbah.articles.utils.unhide
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : ArticlesFragment(), HasLoading {
    private var mAdapter: ArticlesAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel

    private val mObserver = Observer<Resource<List<Article>, HomeViewModel.Action>> { t ->
        when (t.action) {
            HomeViewModel.Action.SHOW_LOADING -> showLoading()
            HomeViewModel.Action.SHOW_ARTICLES_LIST -> updateArticlesList(t.payload)
            HomeViewModel.Action.SHOW_CONNECTION_ERROR -> showConnectionError()
            HomeViewModel.Action.SHOW_AUTH_ERROR -> showAuthError()
        }
    }

    private fun showAuthError() {
        hideLoading()
    }

    private fun showConnectionError() {
        hideLoading()
        tvError.unhide()
        btnRetry.unhide()
        rvArticles.hide()
    }

    private fun updateArticlesList(articles: List<Article>?) {
        hideLoading()
        hideErrorLayout()
        rvArticles.unhide()
        mAdapter?.setData(articles ?: listOf())
    }

    private fun hideErrorLayout() {
        tvError.hide()
        btnRetry.hide()
    }

    override fun initViewModel() {
        homeViewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            )[HomeViewModel::class.java]
    }

    override fun initViews() {
        updateToolBarTitle(getString(R.string.home_fragment_title))
        initRvArticles()
        btnRetry.setOnClickListener {
            homeViewModel.retry()
        }
    }

    private fun initRvArticles() {
        rvArticles.layoutManager = LinearLayoutManager(this.context)
        mAdapter = ArticlesAdapter()
        mAdapter!!.mItemClickListener = object : ArticlesAdapter.ItemClickListener {
            override fun onClicked(view: View, article: Article, position: Int) {
                Log.i(tag, "Article title: ${article.title}")
                findNavController().navigate(
                    R.id.action_nav_home_to_nav_details,
                    bundleOf(ArticleDetailsFragment.ARTICLE_ARG_NAME to article.toString())
                )
            }
        }
        rvArticles.adapter = mAdapter

    }

    override fun subscribe() {
        homeViewModel.articles.observe(
            this,
            mObserver
        )
        homeViewModel.getPopularArticlesForLast7Days()
    }

    override fun unSubscribe() {
        homeViewModel.unSubscribe()
    }

    override fun showLoading() {
        hideErrorLayout()
        rvArticles.hide()
        progressBar.unhide()
    }

    override fun hideLoading() {
        progressBar.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
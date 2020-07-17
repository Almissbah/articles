package com.almissbah.articles.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almissbah.articles.data.remote.model.Article
import com.almissbah.articles.utils.ArticlesUtils
import javax.inject.Inject

class ArticleDetailsViewModel @Inject constructor() : ViewModel() {
    var mArticle: Article? = null
    private val _articleDetails = MutableLiveData<ArticleDetails>()
    val articleDetails: LiveData<ArticleDetails> = _articleDetails

    fun setArticle(data: String) {
        mArticle = ArticlesUtils.getFromJsonString(data)
        val articleDetails = ArticlesUtils.getArticleDetails(mArticle!!)
        _articleDetails.postValue(articleDetails)
    }

    fun unSubscribe() {
        mArticle = null
    }
}
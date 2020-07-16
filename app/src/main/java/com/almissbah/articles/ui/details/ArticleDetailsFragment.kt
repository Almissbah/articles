package com.almissbah.articles.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.almissbah.articles.R
import com.almissbah.articles.ui.base.ArticlesFragment
import javax.inject.Inject

class ArticleDetailsFragment : ArticlesFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var articleDetailsViewModel: ArticleDetailsViewModel
    override fun initViewModel() {
        articleDetailsViewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            )[ArticleDetailsViewModel::class.java]
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }

    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun unSubscribe() {
        TODO("Not yet implemented")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_article_details, container, false)
    }
}
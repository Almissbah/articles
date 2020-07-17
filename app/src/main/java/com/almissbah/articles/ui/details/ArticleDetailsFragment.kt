package com.almissbah.articles.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.almissbah.articles.R
import com.almissbah.articles.data.remote.model.MediaMetaData
import com.almissbah.articles.ui.base.ArticlesFragment
import com.almissbah.articles.utils.AppUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_article_details.*
import javax.inject.Inject

class ArticleDetailsFragment : ArticlesFragment() {
    companion object {
        const val ARTICLE_ARG_NAME = "article"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var articleDetailsViewModel: ArticleDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_article_details, container, false)
    }

    override fun initViewModel() {
        articleDetailsViewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            )[ArticleDetailsViewModel::class.java]
    }

    override fun initViews() {
        updateToolBarTitle(getString(R.string.article_details_title))
        btnReadFull.setOnClickListener {
            AppUtils.openUrlInBrowser(requireContext(), articleDetailsViewModel.mArticle!!.url)
        }
    }

    override fun subscribe() {
        articleDetailsViewModel.articleDetails.observe(this, Observer {
            updateUI(it)
        })
        val data = requireArguments().getString(ARTICLE_ARG_NAME)
        articleDetailsViewModel.setArticle(data!!)
    }

    private fun updateUI(it: ArticleDetails) {
        tvKeywords.text = it.keywords
        tvSection.text = "${it.section} ${it.subSection}"
        tvSource.text = it.source
        byline.text = it.byline
        articleTitle.text = it.title
        articleAbstract.text = it.abstract
        publishDate.text = it.publishedDate
        setArticleImage(it.mediaMetadata)
    }

    private fun setArticleImage(mediaMetadata: MediaMetaData?) {
        if (mediaMetadata != null && (AppUtils.isValidUrl(mediaMetadata.url))) {
            Glide
                .with(this)
                .load(mediaMetadata.url).fitCenter()
                .into(articleImage)
        }
    }

    override fun unSubscribe() {
        articleDetailsViewModel.unSubscribe()
    }

}
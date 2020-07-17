package com.almissbah.articles.utils

import com.almissbah.articles.data.remote.model.Article
import com.almissbah.articles.data.remote.model.MediaMetaData
import com.almissbah.articles.ui.details.ArticleDetails
import com.google.gson.Gson

class ArticlesUtils {
    companion object {
        fun getArticleDetails(article: Article): ArticleDetails {
            var mediaMetaData: MediaMetaData? = null

            if (article.media.isNotEmpty() && article.media.last().metaData.isNotEmpty()) {
                mediaMetaData = article.media.last().metaData.last()
            }
            return ArticleDetails(
                article.title,
                article.url,
                article.byline,
                article.abstract,
                article.source,
                article.updatedAt,
                article.section,
                article.subSection,
                article.keywords, mediaMetaData, article.publishedDate
            )
        }

        fun convertToJsonString(article: Article): String {
            return Gson().toJson(article).toString()
        }

        fun getFromJsonString(string: String): Article? {
            return Gson().fromJson(string, Article::class.java)
        }

        fun trimArticleTitle(title: String): String {
            var newTitle = title
            if (title.length > 100) {
                newTitle = title.substring(0, 100) + ".."
            }
            return newTitle
        }
    }
}
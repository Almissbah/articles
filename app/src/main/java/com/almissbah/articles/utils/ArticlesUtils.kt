package com.almissbah.articles.utils

import com.almissbah.articles.data.remote.model.Article
import com.almissbah.articles.ui.details.ArticleDetails
import com.google.gson.Gson

class ArticlesUtils {
    companion object {
        fun getArticleDetails(article: Article): ArticleDetails {
            return ArticleDetails(
                article.title,
                article.url,
                article.byline,
                article.abstract,
                article.source,
                article.updatedAt,
                article.section,
                article.subSection,
                article.keywords, article.media.last().metaData.last(), article.publishedDate
            )
        }

        fun convertToJsonString(article: Article): String {
            return Gson().toJson(article).toString()
        }

        fun getFromJsonString(string: String): Article? {
            return Gson().fromJson(string, Article::class.java)
        }
    }
}
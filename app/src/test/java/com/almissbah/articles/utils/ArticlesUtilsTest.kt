package com.almissbah.articles.utils

import com.almissbah.articles.MockUpUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticlesUtilsTest {
    private val article = MockUpUtils.getArticleObject()
    private val stringArticle =
        "{\"title\":\"SomeTile\",\"url\":\"https://someUrl.com\",\"byline\":" +
                "\"By Mohammed\",\"abstract\":\"Abstract\",\"source\":\"Android Studio\",\"updated\":\"Today\"," +
                "\"section\":\"Articles\",\"subsection\":\"Fun\",\"adx_keywords\":\"Some Keywords\",\"media\":[]," +
                "\"published_date\":\"Some date\"}"
    private val title =
        "Some long long long long long long long long long long long long long long long long long long long long title"
    private val shortTitle = "Title"

    @Test
    fun test_getArticleDetailsFromArticleObject() {
        val articleDetails = ArticlesUtils.getArticleDetails(article)
        assertEquals(article.title, articleDetails.title)
        assertEquals(article.abstract, articleDetails.abstract)
        assertEquals(article.source, articleDetails.source)
        assertEquals(article.publishedDate, articleDetails.publishedDate)
        assertEquals(article.updatedAt, articleDetails.updatedAt)
    }

    @Test
    fun test_getArticleObjectFromJsonString() {
        val string = ArticlesUtils.convertToJsonString(article)
        assertEquals(string, stringArticle)
    }

    @Test
    fun test_getJsonStringFromArticleObject() {
        val newArticle = ArticlesUtils.getFromJsonString(stringArticle)
        assertEquals(newArticle?.title, article.title)
    }


    @Test
    fun test_trimArticleTitle() {
        val newArticle = ArticlesUtils.trimArticleTitle(title)
        assertEquals(newArticle.length, 63)
        val newArticle2 = ArticlesUtils.trimArticleTitle(shortTitle)
        assertEquals(newArticle2.length, 5)
    }

}
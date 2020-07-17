package com.almissbah.articles

import com.almissbah.articles.data.remote.model.Article

class MockUpUtils {
    companion object {
        fun getArticleObject(): Article {
            return Article(
                "SomeTile",
                "https://someUrl.com",
                "By Mohammed",
                "Abstract",
                "Android Studio",
                "Today",
                "Articles",
                "Fun",
                "Some Keywords", mutableListOf(), "Some date"
            )
        }

        fun getStringArticle(): String {
            return "{\"title\":\"SomeTile\",\"url\":\"https://someUrl.com\",\"byline\":\"By Mohammed\",\"abstract\":\"Abstract\",\"source\":\"Android Studio\",\"updated\":\"Today\",\"section\":\"Articles\",\"subsection\":\"Fun\",\"adx_keywords\":\"Some Keywords\",\"media\":[],\"published_date\":\"Some date\"}"
        }
    }
}
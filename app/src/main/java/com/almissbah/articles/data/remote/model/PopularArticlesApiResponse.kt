package com.almissbah.articles.data.remote.model

import com.google.gson.annotations.SerializedName

data class PopularArticlesApiResponse(
    val status: String,
    @SerializedName("results") val articles: List<Article>
)
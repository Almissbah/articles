package com.almissbah.articles.data.remote.model

import com.google.gson.annotations.SerializedName

data class Article(
    val title: String,
    val url: String,
    val byline: String,
    val abstract: String,
    @SerializedName("published_date") val publishedDate: String
)
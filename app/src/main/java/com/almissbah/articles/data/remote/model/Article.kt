package com.almissbah.articles.data.remote.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Article(
    val title: String,
    val url: String,
    val byline: String,
    val abstract: String,
    @SerializedName("published_date") val publishedDate: String
) {
    companion object {
        fun fromString(string: String): Article? {
            return Gson().fromJson(string, Article::class.java)
        }
    }

    override fun toString(): String {
        return Gson().toJson(this).toString()
    }
}
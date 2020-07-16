package com.almissbah.articles.data.remote

import com.almissbah.articles.data.remote.model.PopularArticlesApiResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface PopularArticlesApiService {

    @GET("/{section}/{period}.json")
    fun getArticles(
        @Path(value = "section") section: String,
        @Path(value = "period") period: Int
    ): Observable<Response<PopularArticlesApiResponse>>
}
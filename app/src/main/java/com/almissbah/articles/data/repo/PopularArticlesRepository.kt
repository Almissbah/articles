package com.almissbah.articles.data.repo

import com.almissbah.articles.data.remote.model.PopularArticlesApiResponse
import com.almissbah.articles.data.remote.model.PopularArticlesRequest
import io.reactivex.Observable
import retrofit2.Response

interface PopularArticlesRepository {
    fun getPopularArticles(request:PopularArticlesRequest):  Observable<Response<PopularArticlesApiResponse>>
}
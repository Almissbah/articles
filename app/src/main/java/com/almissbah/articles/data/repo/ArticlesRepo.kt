package com.almissbah.articles.data.repo

import com.almissbah.articles.data.remote.PopularArticlesApiService
import com.almissbah.articles.data.remote.model.PopularArticlesApiResponse
import com.almissbah.articles.data.remote.model.PopularArticlesRequest
import io.reactivex.Observable
import retrofit2.Response

class ArticlesRepo(
    private val popularArticlesApiService: PopularArticlesApiService
) : ArticlesRepository {

    override fun getPopularArticles(request: PopularArticlesRequest): Observable<Response<PopularArticlesApiResponse>> {
        return popularArticlesApiService.getArticles(request.section, request.period.value)
    }

}
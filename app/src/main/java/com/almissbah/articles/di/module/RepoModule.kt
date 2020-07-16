package com.almissbah.articles.di.module

import com.almissbah.articles.data.remote.PopularArticlesApiService
import com.almissbah.articles.data.repo.PopularArticlesRepo
import com.almissbah.articles.data.repo.PopularArticlesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepoModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideAppRepository(
        articlesApiService: PopularArticlesApiService
    ): PopularArticlesRepository {
        return PopularArticlesRepo(
            articlesApiService
        )
    }

}
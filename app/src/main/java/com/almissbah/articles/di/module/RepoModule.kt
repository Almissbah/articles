package com.almissbah.articles.di.module

import com.almissbah.articles.data.remote.PopularArticlesApiService
import com.almissbah.articles.data.repo.ArticlesRepo
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
    ): ArticlesRepo {
        return ArticlesRepo(
            articlesApiService
        )
    }

}
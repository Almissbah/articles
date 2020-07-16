package com.almissbah.articles.di.module

import com.almissbah.articles.ui.details.ArticleDetailsFragment
import com.almissbah.articles.ui.home.HomeFragment
import com.almissbah.articles.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeArticleDetailsFragment(): ArticleDetailsFragment

}
package com.almissbah.articles.di.component

import android.app.Application
import com.almissbah.articles.ArticlesApplication
import com.almissbah.articles.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class,  RepoModule::class,  AndroidSupportInjectionModule::class, ViewModelModule::class, FragmentModule::class, ActivityModule::class])
interface AppComponent : AndroidInjector<ArticlesApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: ArticlesApplication)
}
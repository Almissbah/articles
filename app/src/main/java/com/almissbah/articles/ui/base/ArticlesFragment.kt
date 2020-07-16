package com.almissbah.articles.ui.base

import android.os.Bundle
import android.view.View
import dagger.android.support.DaggerFragment

abstract class ArticlesFragment : DaggerFragment() {

    abstract fun initViewModel()

    abstract fun initViews()

    abstract fun subscribe()

    abstract fun unSubscribe()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroy() {
        unSubscribe()
        super.onDestroy()
    }

}
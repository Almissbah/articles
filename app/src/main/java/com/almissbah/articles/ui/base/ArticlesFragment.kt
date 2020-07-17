package com.almissbah.articles.ui.base

import android.os.Bundle
import android.view.View
import com.almissbah.articles.MainActivity
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


    fun updateToolBarTitle(title: String) {
        if (isAdded) (this.activity as MainActivity).updateActionBarTitle(title)
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
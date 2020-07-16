package com.almissbah.articles.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.almissbah.articles.R
import com.almissbah.articles.ui.base.ArticlesFragment
import com.almissbah.articles.ui.base.HasLoading
import javax.inject.Inject

class HomeFragment : ArticlesFragment(), HasLoading {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel
    override fun initViewModel() {
        homeViewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            )[HomeViewModel::class.java]
    }

    override fun initViews() {
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
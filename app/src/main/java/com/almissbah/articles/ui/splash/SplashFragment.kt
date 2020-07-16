package com.almissbah.articles.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.almissbah.articles.R
import com.almissbah.articles.ui.base.ArticlesFragment
import javax.inject.Inject

class SplashFragment : ArticlesFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        splashViewModel.onActivityCreated()
    }

    override fun initViewModel() {
        splashViewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            )[SplashViewModel::class.java]
    }

    override fun initViews() {
    }


    override fun subscribe() {
        splashViewModel.action.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_nav_splash_to_nav_home)
        })
    }

    override fun unSubscribe() {
        splashViewModel.unSubscribe()
    }
}
package com.almissbah.articles.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almissbah.articles.data.Resource
import com.almissbah.articles.utils.SPLASH_SCREEN_DELAY_MS
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    enum class Action { NAVIGATE_TO_HOME_SCREEN }

    private var disposable: Disposable? = null
    private val _action = MutableLiveData<Resource<Any, Action>>()
    val action: LiveData<Resource<Any, Action>> = _action

    fun onActivityCreated() {
        showHomeScreenAfterDelay()
    }

    private fun showHomeScreenAfterDelay() {
        disposable = Observable.timer(SPLASH_SCREEN_DELAY_MS, TimeUnit.MILLISECONDS)
            .map {
                _action.postValue(Resource(null, Action.NAVIGATE_TO_HOME_SCREEN, ""))
            }.observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun unSubscribe() {
        disposable?.dispose()
    }
}
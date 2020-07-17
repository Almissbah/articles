package com.almissbah.articles.data.remote

import com.almissbah.articles.data.Resource
import com.almissbah.articles.data.repo.RepoCallback
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import retrofit2.Response
import java.net.HttpURLConnection.*

open class CallbackWrapper<T>(private val callback: HttpCallback<T>) :
    DisposableObserver<Response<T>>() {

    private fun onSuccess(t: Response<T>) {
        if (t.code() == HTTP_OK) {
            callback.onSuccess(t.body())
        } else if (t.code() == HTTP_CREATED) {
            callback.onCreated(t.body())
        } else if (t.code() == HTTP_FORBIDDEN || t.code() == HTTP_UNAUTHORIZED) {
            callback.onForbidden()
        } else if (t.code() == HTTP_NOT_FOUND) {
            callback.onNotFound()
        } else if (t.code() >= HTTP_BAD_REQUEST) {
            callback.onServerError()
        }
    }

    override fun onNext(t: Response<T>) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        if (e is HttpException) {
            val response = e.response()
            if (response.code() == HTTP_FORBIDDEN || response.code() == HTTP_UNAUTHORIZED) {
                callback.onForbidden()
            } else {
                callback.onServerError()
            }
        } else {
            callback.onNetworkError()
        }
    }

    override fun onComplete() {}


    interface HttpCallback<T> {
        fun onCreated(t: T?)
        fun onSuccess(t: T?)
        fun onNetworkError()
        fun onNotFound()
        fun onServerError()
        fun onFail(t: T?, msg: String)
        fun onForbidden()
    }

    abstract class HttpHandler<T>(val callback: RepoCallback<T>) : HttpCallback<T> {
        override fun onServerError() {
            callback.onResult(Resource(null, Resource.Status.SERVER_ERROR, ""))
        }

        override fun onCreated(t: T?) {

        }

        override fun onSuccess(t: T?) {
            callback.onResult(Resource(t, Resource.Status.SUCCESS, ""))
        }

        override fun onFail(t: T?, msg: String) {
            callback.onResult(Resource(null, Resource.Status.FAIL, ""))
        }

        override fun onForbidden() {
            callback.onResult(Resource(null, Resource.Status.FORBIDDEN, ""))
        }

        override fun onNetworkError() {
            callback.onResult(Resource(null, Resource.Status.CONNECTION_ERROR, ""))
        }

        override fun onNotFound() {
            callback.onResult(Resource(null, Resource.Status.NOT_FOUND, ""))
        }
    }
}
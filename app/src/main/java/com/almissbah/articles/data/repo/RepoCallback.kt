package com.almissbah.articles.data.repo

import com.almissbah.articles.data.Resource


interface RepoCallback<T> {
    fun onResult(result: Resource<T, Resource.Status>)
}
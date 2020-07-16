package com.almissbah.articles.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almissbah.articles.data.repo.PopularArticlesRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val popularArticlesRepository: PopularArticlesRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}
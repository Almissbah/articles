package com.almissbah.articles.utils

import android.view.View

fun View.hide() {
    this.visibility = View.GONE
}

fun View.unhide() {
    this.visibility = View.VISIBLE
}
package com.almissbah.articles.utils

import android.content.Context
import android.content.Intent
import android.net.Uri


class AppUtils {
    companion object {
        fun openUrlInBrowser(context: Context, url: String) {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }

        fun isValidUrl(): Boolean {
            return true
        }
    }
}
package com.almissbah.articles.utils

import android.util.Log

class Log {
    companion object {
        fun i(tag: String?, msg: String) {
            if (ENABLE_LOG) Log.i(tag, msg)
        }

        fun d(tag: String?, msg: String) {
            if (ENABLE_LOG) Log.d(tag, msg)
        }

        fun e(tag: String?, msg: String) {
            if (ENABLE_LOG) Log.e(tag, msg)
        }
    }
}
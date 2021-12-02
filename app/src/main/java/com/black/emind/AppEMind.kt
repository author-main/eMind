package com.black.emind

import android.app.Application
import android.content.Context

class AppEMind: Application() {
    init {
        instance = this
    }
    companion object {
        private var instance: AppEMind? = null
        fun applicationContext(): Context =
            instance!!.applicationContext
    }
}
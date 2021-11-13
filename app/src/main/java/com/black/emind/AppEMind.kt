package com.black.emind

import android.app.Application

class AppEMind: Application() {
    init {
        instance = this
    }
    companion object {
        private var instance: AppEMind? = null
        fun applicationContext() =
            instance!!.applicationContext
    }
}
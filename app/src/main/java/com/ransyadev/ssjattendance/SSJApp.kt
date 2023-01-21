package com.ransyadev.ssjattendance

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SSJApp : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: SSJApp
            private set
    }
}

package com.project.timeleaf

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TlApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
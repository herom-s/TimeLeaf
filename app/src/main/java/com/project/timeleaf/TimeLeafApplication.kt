package com.project.timeleaf

import android.app.Application
import com.project.timeleaf.data.TimeLeafContainer
import com.project.timeleaf.data.TimeLeafDataContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TimeLeafApplication : Application() {
    lateinit var container: TimeLeafContainer
    override fun onCreate() {
        super.onCreate()
        container = TimeLeafDataContainer(this)
    }
}
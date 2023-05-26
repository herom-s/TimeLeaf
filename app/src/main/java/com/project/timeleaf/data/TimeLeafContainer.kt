package com.project.timeleaf.data

import android.content.Context

interface TimeLeafContainer  {
    val userRepository: UserRepository
}

class TimeLeafDataContainer(private val context: Context) : TimeLeafContainer {
    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(TimeLeafDatabase.getDatabase(context).userDao())
    }
}
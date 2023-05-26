package com.project.timeleaf.di

import com.project.timeleaf.data.OfflineUserRepository
import com.project.timeleaf.data.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object OfflineUserRepositoryModule {
    @Provides
    fun provideOfflineUserRepository(userDao: UserDao) = OfflineUserRepository(userDao = userDao)
}
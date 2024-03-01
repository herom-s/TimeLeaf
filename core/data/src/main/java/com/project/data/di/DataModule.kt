package com.project.data.di

import com.project.data.repository.OfflineUserRepository
import com.project.data.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsUserRepository(
        userRepository: OfflineUserRepository,
    ): UserRepository
}
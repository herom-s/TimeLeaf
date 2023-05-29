package com.project.timeleaf.di

import com.project.timeleaf.data.OfflineUserRepository
import com.project.timeleaf.data.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindOfflineUser(offlineUserRepository : OfflineUserRepository) : UserRepository
}
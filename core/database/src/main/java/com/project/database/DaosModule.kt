package com.project.database

import com.project.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun provideUserDao(database: TlDatabase): UserDao {
        return database.userDao()
    }
}
package com.project.timeleaf.data

import android.content.Context
import androidx.room.Room
import com.project.timeleaf.data.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class TimeLeafDatabaseModule {
    @Provides
    @Singleton
    fun provideTimeLeafDatabase(@ApplicationContext context: Context): TimeLeafDatabase {
        return Room.databaseBuilder(
            context,
            TimeLeafDatabase::class.java,
            "timeleaf_database"
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: TimeLeafDatabase): UserDao {
        return appDatabase.userDao()
    }
}
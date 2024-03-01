package com.project.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.database.dao.UserDao
import com.project.database.model.UserEntity

private const val DATABASE_VERSION = 1

@Database(
    entities = [
        UserEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false,
)

abstract class TlDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

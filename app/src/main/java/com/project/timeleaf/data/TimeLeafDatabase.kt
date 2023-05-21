package com.project.timeleaf.data

import android.content.ClipData
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.timeleaf.data.room.UserDao

/**
 * Database class with a singleton INSTANCE object.
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class TimeLeafDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: TimeLeafDatabase? = null

        fun getDatabase(context: Context): TimeLeafDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TimeLeafDatabase::class.java, "timeleaf_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}

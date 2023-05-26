package com.project.timeleaf.data.room

import androidx.room.*
import com.project.timeleaf.data.User
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao : BaseDao<User> {

    @Query("SELECT * from user LIMIT 1")
    abstract fun getUser(): Flow<User>
}

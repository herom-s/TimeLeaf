package com.project.timeleaf.data.room

import androidx.room.*
import com.project.timeleaf.data.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao : BaseDao<User> {

    @Query("SELECT * from user LIMIT 1")
    fun getUser(): Flow<User>
}

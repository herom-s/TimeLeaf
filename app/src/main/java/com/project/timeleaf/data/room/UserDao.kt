package com.project.timeleaf.data.room

import androidx.room.*
import com.project.timeleaf.data.User

@Dao
abstract class UserDao : BaseDao<User> {

    @Query("SELECT * from user LIMIT 1")
    abstract fun getUser(): User?
}

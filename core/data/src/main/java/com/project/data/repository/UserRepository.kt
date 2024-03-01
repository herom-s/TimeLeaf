package com.project.data.repository

import com.project.model.data.User
import kotlinx.coroutines.flow.Flow


interface UserRepository  {
    /**
     * Retrieve an user from the given data source
     */
    suspend fun getUser(): Flow<User>

    /**
     * Insert user in the data source
     */
    suspend fun insertUser(user: User)

    /**
     * Update user in the data source
     */
    suspend fun updateUser(user: User)


    /**
     * Delete user from the data source
     */
    suspend fun deleteUser(user: User)
}

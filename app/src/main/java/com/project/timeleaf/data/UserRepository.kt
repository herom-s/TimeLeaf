package com.project.timeleaf.data


interface UserRepository  {
    /**
     * Retrieve an user from the given data source that matches with the [userId].
     */
    fun getUser(): User?

    /**
     * Insert user in the data source
     */
    suspend fun insertUser(user: User)

    /**
     * Delete user from the data source
     */
    suspend fun deleteUser(user: User)

    /**
     * Update user in the data source
     */
    suspend fun updateUser(user: User)
}

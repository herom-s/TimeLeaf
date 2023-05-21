package com.project.timeleaf.data

import com.project.timeleaf.data.room.UserDao
import kotlinx.coroutines.flow.Flow


class OfflineUserRepository(private val userDao: UserDao) : UserRepository {
    override fun getUserStream(): Flow<User?> = userDao.getUser()

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun updateUser(user: User) = userDao.update(user)
}

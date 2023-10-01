package com.project.timeleaf.data

import com.project.timeleaf.data.room.UserDao
import javax.inject.Inject


class OfflineUserRepository @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override fun getUser(): User? = userDao.getUser()

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun updateUser(user: User) = userDao.update(user)
}

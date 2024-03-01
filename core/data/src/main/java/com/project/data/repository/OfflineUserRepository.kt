package com.project.data.repository

import com.project.data.model.asEntity
import com.project.database.dao.UserDao
import com.project.database.model.asExternalModel
import com.project.model.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineUserRepository @Inject constructor(
    private val userDao: UserDao,
) : UserRepository {
    override suspend fun getUser(): Flow<User> = userDao.getUserEntity().map { it.asExternalModel() }
    override suspend fun insertUser(user: User) = userDao.insertOrReplaceUserEntity(user.asEntity())

    override suspend fun updateUser(user: User) = userDao.updateOrReplaceUserEntity(user.asEntity())

    override suspend fun deleteUser(user: User) = userDao.deleteUserEntity(user.asEntity())
}
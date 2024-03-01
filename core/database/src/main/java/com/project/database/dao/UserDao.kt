package com.project.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.project.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query(value = " SELECT * FROM user")
    fun getUserEntity(): Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceUserEntity(user : UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateOrReplaceUserEntity(user : UserEntity)

    @Delete
    suspend fun deleteUserEntity(user : UserEntity)
}
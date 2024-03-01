package com.project.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.model.data.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: Int = 0,
    val name: String = "",
    val gender: String = "",
    val age: Int = 0,
    @ColumnInfo(name = "lvl_physical_activity")
    val lvlPhysicalActivity: Double = 0.0,
    val weight: Double = 0.0,
    val height: Double = 0.0,
    val bmi: Double = 0.0,
    val bmr: Double = 0.0,
    val tev: Double = 0.0,
    @ColumnInfo(name = "should_hide_onboarding")
    val shouldHideOnboarding: Boolean = false,
)

fun UserEntity?.asExternalModel() = User(
    name = this?.name ?: "",
    gender = this?.gender ?: "",
    age = this?.age ?: 0,
    lvlPhysicalActivity = this?.lvlPhysicalActivity ?: 0.0,
    weight = this?.weight ?: 0.0,
    height = this?.height ?: 0.0,
    bmi = this?.bmi ?: 0.0 ,
    bmr = this?.bmr ?: 0.0,
    tev = this?.tev ?: 0.0,
    shouldHideOnboarding = this?.shouldHideOnboarding ?: false
)

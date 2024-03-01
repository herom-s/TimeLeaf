package com.project.data.model

import com.project.database.model.UserEntity
import com.project.model.data.User

fun User.asEntity() = UserEntity(
    name = name,
    gender = gender,
    age = age,
    lvlPhysicalActivity = lvlPhysicalActivity,
    weight = weight,
    height = height,
    bmi = bmi,
    bmr = bmr,
    tev = tev,
    shouldHideOnboarding = shouldHideOnboarding
)
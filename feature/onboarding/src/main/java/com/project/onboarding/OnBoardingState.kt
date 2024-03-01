package com.project.onboarding


import com.project.model.data.User
import com.project.onboarding.util.calculateBmi
import com.project.onboarding.util.calculateBmr
import com.project.onboarding.util.calculateTev

data class OnBoardingUiState(
    val name: String = "",
    val gender: String = "",
    val age: Int = 0,
    val lvlPhysicalActivity: Double = 0.0,
    val weight: Double = 0.0,
    val height: Double = 0.0,
    val shouldHideOnboarding: Boolean = false,
    val isNameValid: Boolean = false,
    val isAgeValid: Boolean = false,
    val isWeightValid: Boolean = false,
    val isHeightValid: Boolean = false,
    val errorMessage: String = ""
)

fun OnBoardingUiState.toUser(): User = User(
    name = name,
    gender = gender,
    age = age,
    lvlPhysicalActivity = lvlPhysicalActivity,
    weight = weight,
    height = height,
    bmi = calculateBmi(weight, height),
    bmr = calculateBmr(weight, height,age, gender),
    tev = calculateTev(calculateBmr(weight, height,age, gender), lvlPhysicalActivity),
    shouldHideOnboarding = shouldHideOnboarding
)
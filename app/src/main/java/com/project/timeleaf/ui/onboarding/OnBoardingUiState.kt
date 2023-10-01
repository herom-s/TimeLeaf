package com.project.timeleaf.ui.onboarding

import com.project.timeleaf.data.User

data class OnBoardingUiState(
    val userId: Int = 0,
    val name: String = "",
    val gender: String = "",
    val age: String = "",
    val lvlPhysicalActivity: String = "",
    val weight: String = "",
    val height: String = "",
    val actionEnabled: Boolean = false
)

fun OnBoardingUiState.toUser(): User = User(
    userId = userId,
    name = name,
    gender = gender,
    age = age.toIntOrNull() ?: 0,
    lvlPhysicalActivity = lvlPhysicalActivity.toDoubleOrNull() ?: 0.0,
    weight = weight.toDoubleOrNull() ?: 0.0,
    height = height.toDoubleOrNull() ?: 0.0,
    bmi = 0.0,
    bmr = 0.0,
    tev = 0.0
)

fun OnBoardingUiState.isValid(): Boolean {
    return name.isNotBlank() && gender.isNotBlank() && age.isNotBlank() && lvlPhysicalActivity.isNotBlank() && weight.isNotBlank() && height.isNotBlank()
}
package com.project.timeleaf.ui.state

import com.project.timeleaf.data.User


data class UserUiState(
    val user_id: Int = 0,
    val name: String = "",
    val gender: String = "",
    val age: String = "",
    val lvl_physical_activity: String = "",
    val weight: String = "",
    val height: String = "",
    val bmi: String = "",
    val bmr: String = "",
    val tev: String = "",
    val actionEnabled: Boolean = false
)


fun UserUiState.toUser(): User = User(
    user_id = user_id,
    name = name,
    gender = gender,
    age = age.toIntOrNull() ?: 0,
    lvl_physical_activity = lvl_physical_activity.toDoubleOrNull() ?: 0.0,
    weight = weight.toDoubleOrNull() ?: 0.0,
    height = height.toDoubleOrNull() ?: 0.0,
    bmi = bmi.toDoubleOrNull() ?: 0.0,
    bmr = bmr.toDoubleOrNull() ?: 0.0,
    tev = tev.toDoubleOrNull() ?: 0.0
)

fun User.toUserUiState(actionEnabled: Boolean = false): UserUiState = UserUiState(
    user_id = user_id,
    name = name,
    gender = gender,
    age = age.toString(),
    lvl_physical_activity = lvl_physical_activity.toString(),
    weight = weight.toString(),
    height = height.toString(),
    bmi = bmi.toString(),
    bmr = bmr.toString(),
    tev = tev.toString(),
    actionEnabled = actionEnabled
)

fun UserUiState.isValid(): Boolean {
    return name.isNotBlank() && gender.isNotBlank() && age.isNotBlank() && lvl_physical_activity.isNotBlank() && weight.isNotBlank() && height.isNotBlank()
}
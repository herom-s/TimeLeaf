package com.project.model.data
data class User(
    val name: String = "",
    val gender: String = "",
    val age: Int = 0,
    val lvlPhysicalActivity: Double = 0.0,
    val weight: Double = 0.0,
    val height: Double = 0.0,
    val bmi: Double = 0.0,
    val bmr: Double = 0.0,
    val tev: Double = 0.0,
    val shouldHideOnboarding: Boolean = false,
)
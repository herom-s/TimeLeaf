package com.project.onboarding.util

fun calculateBmi(weight: Double, height: Double): Double {
    return weight / (height * height)
}
fun calculateBmr(
    weight: Double,
    height: Double,
    age: Int,
    gender: String,
): Double{

    return when (gender) {
        "Male" -> 10 * weight + 6.25 * height - 5 * age + 5
        "Female" -> 10 * weight + 6.25 * height - 5 * age - 161
        else -> throw IllegalArgumentException("Invalid gender: $gender")
    }
}
fun calculateTev(
    bmr: Double,
    activityLevel: Double
): Double {
    return bmr * activityLevel
}
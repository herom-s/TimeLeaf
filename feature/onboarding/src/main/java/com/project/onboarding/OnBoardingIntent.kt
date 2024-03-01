package com.project.onboarding

sealed class OnBoardingIntent {
    data class NameChanged(val name: String) : OnBoardingIntent()
    data class GenderChanged(val gender: String) : OnBoardingIntent()
    data class AgeChanged(val age: String) : OnBoardingIntent()
    data class LvlPhysicalActivityChanged(val lvlPhysicalActivity: String) : OnBoardingIntent()
    data class WeightChanged(val weight: String) : OnBoardingIntent()
    data class HeightChanged(val height: String) : OnBoardingIntent()
    data object OnBoardingSubmit : OnBoardingIntent()
}

sealed class OnboardingEffect {
    data class OnBoardingError(val message: String) : OnboardingEffect()
    data object ShowLoadingIndicator : OnboardingEffect()
    data object HideLoadingIndicator : OnboardingEffect()
    data object OnBoardingSuccess : OnboardingEffect()
}
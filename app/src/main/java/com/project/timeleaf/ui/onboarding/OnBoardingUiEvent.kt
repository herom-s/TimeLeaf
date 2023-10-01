package com.project.timeleaf.ui.onboarding

sealed class OnBoardingUiEvent {
    data class NameChanged(val name: String) : OnBoardingUiEvent()
    data class GenderChanged(val gender: String) : OnBoardingUiEvent()
    data class AgeChanged(val age: String) : OnBoardingUiEvent()
    data class LvlPhysicalActivityChanged(val lvlPhysicalActivity: String) : OnBoardingUiEvent()
    data class WeightChanged(val weight: String) : OnBoardingUiEvent()
    data class HeightChanged(val height: String) : OnBoardingUiEvent()
    data class OnBoardingCompleted(val completed: Boolean) : OnBoardingUiEvent()
}
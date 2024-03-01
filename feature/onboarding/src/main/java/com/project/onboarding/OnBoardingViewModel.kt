package com.project.onboarding

import androidx.lifecycle.ViewModel
import com.project.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnBoardingUiState())
    var uiState: StateFlow<OnBoardingUiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableStateFlow<OnboardingEffect?>(null)
    val uiEffect: StateFlow<OnboardingEffect?> = _uiEffect

    fun handleIntent(intent: OnBoardingIntent) {
        when (intent) {
            is OnBoardingIntent.NameChanged -> {
                _uiState.update { it.copy(name = intent.name.trim()) }
            }

            is OnBoardingIntent.GenderChanged -> {
                _uiState.update { it.copy(gender = intent.gender) }
            }

            is OnBoardingIntent.AgeChanged -> {
                _uiState.update { it.copy(age = intent.age.toInt()) }
            }

            is OnBoardingIntent.LvlPhysicalActivityChanged -> {
                _uiState.update { it.copy(lvlPhysicalActivity = intent.lvlPhysicalActivity.toDouble()) }
            }

            is OnBoardingIntent.WeightChanged -> {
                _uiState.update { it.copy(weight = intent.weight.toDouble()) }
            }

            is OnBoardingIntent.HeightChanged -> {
                _uiState.update { it.copy(height = intent.height.toDouble()) }
            }

            is OnBoardingIntent.OnBoardingSubmit -> {
                _uiEffect.value = OnboardingEffect.ShowLoadingIndicator
                if(_uiState.value.isNameValid && _uiState.value.isAgeValid && _uiState.value.isHeightValid && _uiState.value.isWeightValid) {
                    _uiState.update { it.copy(shouldHideOnboarding = true) }
                    saveUserInput()
                }
            }
        }
    }

    fun validateUserName(name: String): Boolean{
        return if (name.isEmpty()){
            _uiState.update { it.copy(isNameValid = false) }
            _uiEffect.value = OnboardingEffect.OnBoardingError("Please enter your name")
            false
        }else{
            _uiState.update { it.copy(isNameValid = true) }
            true
        }
    }
   fun validateUserAge(age: String): Boolean{
        return if (age.toInt() <= 0) {
            _uiState.update { it.copy(isAgeValid = false) }
            _uiEffect.value = OnboardingEffect.OnBoardingError("Please enter a valid age")
            false
        }else{
            _uiState.update { it.copy(isAgeValid = true) }
            true
        }
    }

    fun validateUserWeight(weight: String): Boolean{
        return try {
            if (weight.toDouble() <= 0.0) {
                _uiState.update { it.copy(isWeightValid = false) }
                _uiEffect.value = OnboardingEffect.OnBoardingError("Please enter a valid weight")
                false
            } else {
                _uiState.update { it.copy(isWeightValid = true) }
                true
            }
        }catch (e : NumberFormatException){
            _uiState.update { it.copy(isWeightValid = false) }
            _uiEffect.value = OnboardingEffect.OnBoardingError("Please enter a valid weight")
            false
        }
    }

    fun validateUserHeight(height: String): Boolean{
        return try {
            if (height.toDouble() <= 0) {
                _uiState.update { it.copy(isHeightValid = false) }
                _uiEffect.value = OnboardingEffect.OnBoardingError("Please enter a valid height")
                false
            } else {
                _uiState.update { it.copy(isHeightValid = true) }
                true
            }
        }catch (e : NumberFormatException){
            _uiState.update { it.copy(isHeightValid = false) }
            _uiEffect.value = OnboardingEffect.OnBoardingError("Please enter a valid height")
            false
        }
    }

    private fun saveUserInput() {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.insertUser(_uiState.value.toUser())
            _uiEffect.value = OnboardingEffect.OnBoardingSuccess
            _uiEffect.value = OnboardingEffect.HideLoadingIndicator
        }
    }
}
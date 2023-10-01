package com.project.timeleaf.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.timeleaf.data.DataStoreRepository
import com.project.timeleaf.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val UserRepository: UserRepository,
    private val dataRepository: DataStoreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnBoardingUiState())
    var uiState: StateFlow<OnBoardingUiState> = _uiState.asStateFlow()

    fun onEvent(event: OnBoardingUiEvent) {
        when (event) {
            is OnBoardingUiEvent.NameChanged -> {
                _uiState.update { it.copy(name = event.name) }
            }

            is OnBoardingUiEvent.GenderChanged-> {
                _uiState.update { it.copy(gender = event.gender) }
            }

            is OnBoardingUiEvent.AgeChanged -> {
                _uiState.update { it.copy(age = event.age) }
            }

            is OnBoardingUiEvent.LvlPhysicalActivityChanged -> {
                _uiState.update { it.copy(lvlPhysicalActivity = event.lvlPhysicalActivity) }
            }

            is OnBoardingUiEvent.HeightChanged -> {
                _uiState.update { it.copy(height = event.height) }
            }

            is OnBoardingUiEvent.WeightChanged -> {
                _uiState.update { it.copy(weight = event.weight) }
            }

            is OnBoardingUiEvent.OnBoardingCompleted -> {
                viewModelScope.launch(Dispatchers.IO) {
                    dataRepository.saveOnBoardingState(completed = event.completed)
                }
            }
        }
    }

    suspend fun saveUser() {
        if (_uiState.value.isValid()) {
            UserRepository.insertUser(_uiState.value.toUser())
        }
    }
}
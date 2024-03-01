package com.project.timeleaf.ui.coach

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.data.repository.UserRepository
import com.project.model.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CoachUiState(
    val userId: Int = 0,
    val name: String = "",
    val gender: String = "",
    val age: String = "",
    val lvlPhysicalActivity: String = "",
    val weight: String = "",
    val height: String = "",
    val bmi: String = "",
    val bmr: String = "",
    val tev: String = "",
)

fun User.toCoachUiState(): CoachUiState = CoachUiState(
    name = name,
    gender = gender,
    age = age.toString(),
    lvlPhysicalActivity = lvlPhysicalActivity.toString(),
    weight = weight.toString(),
    height = height.toString(),
    bmi = bmi.toString(),
    bmr = bmr.toString(),
    tev = tev.toString()
)

@HiltViewModel
class CoachViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoachUiState())
    var uiState: StateFlow<CoachUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchUserInfo()
        }
    }

    private suspend fun fetchUserInfo() {
        userRepository.getUser().collect { user ->
            _uiState.value = user.toCoachUiState()
        }
    }
}
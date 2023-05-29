package com.project.timeleaf.ui.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.timeleaf.data.DataStoreRepository
import com.project.timeleaf.data.UserRepository
import com.project.timeleaf.ui.state.UserUiState
import com.project.timeleaf.ui.state.isValid
import com.project.timeleaf.ui.state.toUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val UserRepository: UserRepository,
    private val dataRepository: DataStoreRepository
) : ViewModel() {

    var userUiState by mutableStateOf(UserUiState())
    private set

    fun updateUiState(newUserUiState: UserUiState) {
        userUiState= newUserUiState.copy( actionEnabled = newUserUiState.isValid())
    }

    suspend fun saveUser() {
        if (userUiState.isValid()) {
            UserRepository.insertUser(userUiState.toUser())
        }
    }

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.saveOnBoardingState(completed = completed)
        }
    }

}
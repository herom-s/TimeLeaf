package com.project.timeleaf.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.timeleaf.data.DataStoreRepository
import com.project.timeleaf.data.OfflineUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val offlineUserRepository: OfflineUserRepository,
    private val dataRepository: DataStoreRepository
) : ViewModel() {

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.saveOnBoardingState(completed = completed)
        }
    }

}
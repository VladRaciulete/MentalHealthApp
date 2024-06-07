package com.example.mentalhealth.presentation.profile.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealth.R
import com.example.mentalhealth.presentation.profile.SettingsItem
import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.domain.usecase.profile.LoadUserDataUseCase
import com.example.mentalhealth.domain.usecase.profile.LogOutUseCase
import com.example.mentalhealth.presentation.AppStateViewModel
import com.example.mentalhealth.utils.AuthState
import com.example.mentalhealth.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val logOutUseCase: LogOutUseCase,
    val loadUserDataUseCase: LoadUserDataUseCase,
    val appStateViewModel: AppStateViewModel
) : ViewModel() {
    var currentUserData = mutableStateOf(User())

    init {
        //loadUserData()
    }

    fun logOut() {
        viewModelScope.launch {
            try {
                appStateViewModel.uiState.value = UiState.Loading
                val logOutResult = logOutUseCase()

                if(logOutResult.isSuccess){
                    appStateViewModel.authState.value = AuthState.Unauthenticated
                    appStateViewModel.uiState.value = UiState.Idle
                    currentUserData.value = User()
                }
                else {
                    appStateViewModel.uiState.value = UiState.Error("Logout failed")
                }
            } catch (e: Exception) {
                appStateViewModel.uiState.value = UiState.Error(e.message ?: "Logout error")
            }
        }
    }

    fun loadUserData() {
        viewModelScope.launch {
            try {
                val user = loadUserDataUseCase()
                if (user != null){
                    currentUserData.value = user
                }
                else {
                }
            } catch (e: Exception) {
                appStateViewModel.uiState.value = UiState.Error(e.message ?: "User data loading error")
            }
        }
    }
}


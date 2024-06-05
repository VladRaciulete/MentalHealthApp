package com.example.mentalhealth.presentation.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealth.domain.usecase.auth.CheckUserAuthenticatedUseCase
import com.example.mentalhealth.domain.usecase.auth.LogInUseCase
import com.example.mentalhealth.domain.usecase.validator.Validator
import com.example.mentalhealth.presentation.AppStateViewModel
import com.example.mentalhealth.utils.AuthState
import com.example.mentalhealth.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    val logInUseCase: LogInUseCase,
    val appStateViewModel: AppStateViewModel,
    val checkUserAuthenticatedUseCase: CheckUserAuthenticatedUseCase
) : ViewModel() {
    var emailAddress = mutableStateOf("")
    var password = mutableStateOf("")
    var passwordVisible = mutableStateOf(false)

    var emailAddressShowError = mutableStateOf(false)
    var passwordShowError = mutableStateOf(false)

    fun validateFields() : Boolean {
        return Validator.validateLogInFields(emailAddress.value, password.value)
    }

    fun logIn() {
        viewModelScope.launch {
            appStateViewModel.authState.value = AuthState.LoggingIn
            appStateViewModel.uiState.value = UiState.Loading

            val logInResult = logInUseCase(emailAddress.value,password.value)
            appStateViewModel.uiState.value = if (logInResult.isSuccess) UiState.Success else UiState.Error(logInResult.exceptionOrNull()?.message ?: "login error")

            if (logInResult.isSuccess) {
                appStateViewModel.authState.value = AuthState.Authenticated
            }
        }
    }
}
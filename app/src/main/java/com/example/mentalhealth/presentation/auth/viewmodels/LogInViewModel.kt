package com.example.mentalhealth.presentation.auth.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealth.domain.usecase.auth.CheckUserAuthenticatedUseCase
import com.example.mentalhealth.domain.usecase.auth.LogInUseCase
import com.example.mentalhealth.utils.Validator
import com.example.mentalhealth.presentation.AppStateViewModel
import com.example.mentalhealth.utils.AuthState
import com.example.mentalhealth.utils.ErrorEvent
import com.example.mentalhealth.utils.SuccessEvent
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

    fun validateFields(): Boolean {
        return Validator.validateLogInFields(emailAddress.value, password.value)
    }

    fun logIn() {
        viewModelScope.launch {
            try {
                appStateViewModel.authState.value = AuthState.LoggingIn
                appStateViewModel.uiState.value = UiState.Loading

                val logInResult = logInUseCase(emailAddress.value, password.value)
                appStateViewModel.uiState.value =
                    if (logInResult.isSuccess)
                        UiState.Success(SuccessEvent.SUCCESS)
                    else
                        UiState.Error(logInResult.exceptionOrNull()?.message ?: ErrorEvent.LOGIN_ERROR)

                if (logInResult.isSuccess) {
                    appStateViewModel.authState.value = AuthState.Authenticated
                } else {
                    appStateViewModel.authState.value = AuthState.Unauthenticated
                }

                resetViewModelFields()
            } catch (e: Exception) {
                appStateViewModel.authState.value = AuthState.Unauthenticated
            }
        }
    }

    fun resetViewModelFields() {
        emailAddress.value = ""
        password.value = ""
        passwordVisible.value = false
        emailAddressShowError.value = false
        passwordShowError.value = false
    }
}
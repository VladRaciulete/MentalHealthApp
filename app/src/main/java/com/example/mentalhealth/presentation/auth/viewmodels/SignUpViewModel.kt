package com.example.mentalhealth.presentation.auth.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.domain.usecase.auth.SignUpUseCase
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
class SignUpViewModel @Inject constructor(
    val signUpUseCase: SignUpUseCase,
    val appStateViewModel: AppStateViewModel
) : ViewModel() {
    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var emailAddress = mutableStateOf("")
    var password = mutableStateOf("")
    var passwordVisible = mutableStateOf(false)
    var birthDate = mutableStateOf("")
    var gender = mutableStateOf("")
    var studies = mutableStateOf("")
    var occupation = mutableStateOf("")
    var maritalStatus = mutableStateOf("")
    var livingArea = mutableStateOf("")
    var publicFigure = mutableStateOf("")
    var currentStep = mutableStateOf(1)
    var firstNameShowError = mutableStateOf(false)
    var lastNameShowError = mutableStateOf(false)
    var emailAddressShowError = mutableStateOf(false)
    var passwordShowError = mutableStateOf(false)
    var birthDateShowError = mutableStateOf(false)
    var genderShowError = mutableStateOf(false)
    var studiesShowError = mutableStateOf(false)
    var occupationShowError = mutableStateOf(false)
    var maritalStatusShowError = mutableStateOf(false)
    var livingAreaShowError = mutableStateOf(false)
    var publicFigureShowError = mutableStateOf(false)

    fun validateFieldsScreen1(): Boolean {
        return Validator.validateSignUpScreen1(
            firstName = firstName.value,
            lastName = lastName.value,
            emailAddress = emailAddress.value,
            password = password.value
        )
    }

    fun validateFieldsScreen2(): Boolean {
        return Validator.validateSignUpScreen2(
            date = birthDate.value,
            gender = gender.value,
            studies = studies.value,
            occupation = occupation.value
        )
    }

    fun validateFieldsScreen3(): Boolean {
        return Validator.validateSignUpScreen3(
            maritalStatus = maritalStatus.value,
            livingArea = livingArea.value,
            publicFigure = publicFigure.value
        )
    }

    fun signUp() {
        viewModelScope.launch {
            try {
                appStateViewModel.authState.value = AuthState.SigningUp
                appStateViewModel.uiState.value = UiState.Loading

                val signUpResult = signUpUseCase(
                    emailAddress.value,
                    password.value,
                    User(
                        firstName.value,
                        lastName.value,
                        birthDate.value,
                        gender.value,
                        studies.value,
                        occupation.value,
                        maritalStatus.value,
                        livingArea.value,
                        publicFigure.value
                    )
                )
                appStateViewModel.uiState.value =
                    if (signUpResult.isSuccess)
                        UiState.Success(SuccessEvent.USER_ACCOUNT_CREATED)
                    else
                        UiState.Error(
                            signUpResult.exceptionOrNull()?.message ?: ErrorEvent.SIGNUP_ERROR
                        )

                if (signUpResult.isSuccess) {
                    appStateViewModel.authState.value = AuthState.Authenticated
                } else {
                    appStateViewModel.authState.value = AuthState.Unauthenticated
                }
                resetViewModelFields()
            } catch (e: Exception) {
                appStateViewModel.authState.value = AuthState.Unauthenticated
                appStateViewModel.uiState.value =
                    UiState.Error(e.message ?: ErrorEvent.SIGNUP_ERROR)
            }
        }
    }

    fun resetViewModelFields() {
        firstName.value = ""
        lastName.value = ""
        emailAddress.value = ""
        password.value = ""
        birthDate.value = ""
        gender.value = ""
        studies.value = ""
        occupation.value = ""
        maritalStatus.value = ""
        livingArea.value = ""
        publicFigure.value = ""
        firstNameShowError.value = false
        lastNameShowError.value = false
        emailAddressShowError.value = false
        passwordShowError.value = false
        birthDateShowError.value = false
        genderShowError.value = false
        studiesShowError.value = false
        occupationShowError.value = false
        maritalStatusShowError.value = false
        livingAreaShowError.value = false
        publicFigureShowError.value = false
    }
}
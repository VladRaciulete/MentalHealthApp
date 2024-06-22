package com.example.mentalhealth.presentation.profile.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.domain.usecase.profile.LoadUserDataUseCase
import com.example.mentalhealth.domain.usecase.profile.LogOutUseCase
import com.example.mentalhealth.domain.usecase.profile.UpdateUserDataUseCase
import com.example.mentalhealth.presentation.AppStateViewModel
import com.example.mentalhealth.utils.AuthState
import com.example.mentalhealth.utils.ErrorEvent
import com.example.mentalhealth.utils.SuccessEvent
import com.example.mentalhealth.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val logOutUseCase: LogOutUseCase,
    val loadUserDataUseCase: LoadUserDataUseCase,
    val updateUserDataUseCase: UpdateUserDataUseCase,
    val appStateViewModel: AppStateViewModel
) : ViewModel() {
    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var birthDate = mutableStateOf("")
    var gender = mutableStateOf("")
    var studies = mutableStateOf("")
    var occupation = mutableStateOf("")
    var maritalStatus = mutableStateOf("")
    var livingArea = mutableStateOf("")
    var publicFigure = mutableStateOf("")

    var firstNameShowError = mutableStateOf(false)
    var lastNameShowError = mutableStateOf(false)
    var birthDateShowError = mutableStateOf(false)
    var genderShowError = mutableStateOf(false)
    var studiesShowError = mutableStateOf(false)
    var occupationShowError = mutableStateOf(false)
    var maritalStatusShowError = mutableStateOf(false)
    var livingAreaShowError = mutableStateOf(false)
    var publicFigureShowError = mutableStateOf(false)

    fun logOut() {
        viewModelScope.launch {
            try {
                appStateViewModel.uiState.value = UiState.Loading
                val logOutResult = logOutUseCase()

                if (logOutResult.isSuccess) {
                    appStateViewModel.authState.value = AuthState.Unauthenticated
                    appStateViewModel.uiState.value = UiState.Success(SuccessEvent.LOGOUT_SUCCESS)
                    resetViewModelFields()
                } else {
                    appStateViewModel.uiState.value = UiState.Error(ErrorEvent.LOGOUT_ERROR)
                }
            } catch (e: Exception) {
                appStateViewModel.uiState.value =
                    UiState.Error(e.message ?: ErrorEvent.LOGOUT_ERROR)
            }
        }
    }

    fun loadUserData() {
        viewModelScope.launch {
            try {
                appStateViewModel.uiState.value = UiState.Loading
                val user = loadUserDataUseCase()

                if (user != null) {
                    firstName.value = user.firstName
                    lastName.value = user.lastName
                    birthDate.value = user.birthDate
                    gender.value = user.gender
                    studies.value = user.studies
                    occupation.value = user.occupation
                    maritalStatus.value = user.maritalStatus
                    livingArea.value = user.livingArea
                    publicFigure.value = user.publicFigure

                    appStateViewModel.uiState.value =
                        UiState.Success(SuccessEvent.USER_ACCOUNT_LOADED)
                } else {
                    appStateViewModel.uiState.value =
                        UiState.Error(ErrorEvent.ERROR_RETRIEVING_USER_DATA)
                }
            } catch (e: Exception) {
                appStateViewModel.uiState.value =
                    UiState.Error(e.message ?: ErrorEvent.ERROR_RETRIEVING_USER_DATA)
            }
        }
    }

    fun updateUserData() {
        viewModelScope.launch {
            try {
                val result = updateUserDataUseCase(
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
                    if (result.isSuccess)
                        UiState.Success(SuccessEvent.USER_ACCOUNT_UPDATED)
                    else
                        UiState.Error(
                            result.exceptionOrNull()?.message ?: ErrorEvent.ERROR_UPDATING_USER_DATA
                        )

                resetViewModelFields()
            } catch (e: Exception) {
                appStateViewModel.uiState.value =
                    UiState.Error(e.message ?: ErrorEvent.ERROR_UPDATING_USER_DATA)
            }
        }
    }

    fun resetViewModelFields() {
        firstName.value = ""
        lastName.value = ""
        birthDate.value = ""
        gender.value = ""
        studies.value = ""
        occupation.value = ""
        maritalStatus.value = ""
        livingArea.value = ""
        publicFigure.value = ""
    }
}


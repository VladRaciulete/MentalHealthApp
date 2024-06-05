package com.example.mentalhealth.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mentalhealth.utils.AuthState
import com.example.mentalhealth.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppStateViewModel @Inject constructor() : ViewModel() {
    var authState = mutableStateOf<AuthState>(AuthState.Unauthenticated)
    var uiState = mutableStateOf<UiState>(UiState.Idle)
}
package com.example.mentalhealth.utils

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object LoggingIn : AuthState()
    object SigningUp : AuthState()
}
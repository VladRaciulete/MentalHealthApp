package com.example.mentalhealth.domain.usecase.auth

import com.example.mentalhealth.domain.repository.AuthenticationRepository

class CheckUserAuthenticatedUseCase(private val authRepository: AuthenticationRepository) {
    operator fun invoke(): Boolean {
        return authRepository.isUserAuthenticatedInFirebase()
    }
}
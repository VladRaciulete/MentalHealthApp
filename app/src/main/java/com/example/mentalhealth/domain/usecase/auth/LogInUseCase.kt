package com.example.mentalhealth.domain.usecase.auth

import com.example.mentalhealth.domain.repository.AuthenticationRepository

class LogInUseCase(private val authRepository: AuthenticationRepository) {
    operator fun invoke(emailAddress: String, password: String): Result<Unit> {
        return authRepository.firebaseLogIn(emailAddress, password)
    }
}
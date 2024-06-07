package com.example.mentalhealth.domain.usecase.profile

import com.example.mentalhealth.domain.repository.AuthenticationRepository

class LogOutUseCase(private val authRepository: AuthenticationRepository) {
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.firebaseLogOut()
    }
}
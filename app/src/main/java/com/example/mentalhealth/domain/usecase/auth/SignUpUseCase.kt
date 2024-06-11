package com.example.mentalhealth.domain.usecase.auth

import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.domain.repository.AuthenticationRepository

class SignUpUseCase(private val authRepository: AuthenticationRepository) {
    suspend operator fun invoke(emailAddress: String, password: String, user: User): Result<Unit> {
        return authRepository.firebaseSignUp(emailAddress, password, user)
    }
}

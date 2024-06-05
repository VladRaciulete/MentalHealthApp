package com.example.mentalhealth.domain.usecase.account

import com.example.mentalhealth.domain.repository.AuthenticationRepository

class LogOutUseCase(private val authRepository: AuthenticationRepository) {
    operator fun invoke(){
        return authRepository.firebaseLogOut()
    }
}
package com.example.mentalhealth.domain.usecase.auth

import com.example.mentalhealth.domain.repository.AuthenticationRepository

class SignUpUseCase(private val authRepository: AuthenticationRepository) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        emailAddress: String,
        password: String,
        date: String,
        gender: String,
        profession: String,
        occupation: String,
        maritalStatus: String,
        livingArea: String,
        publicFigure: String): Result<Unit>
    {
        return authRepository.firebaseSignUp(
            firstName,
            lastName,
            emailAddress,
            password,
            date,
            gender,
            profession,
            occupation,
            maritalStatus,
            livingArea,
            publicFigure)
    }
}

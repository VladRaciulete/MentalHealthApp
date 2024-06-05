package com.example.mentalhealth.domain.repository

interface AuthenticationRepository {
    fun isUserAuthenticatedInFirebase() : Boolean

    suspend fun firebaseSignUp(
        firstName: String,
        lastName: String,
        emailAddress: String,
        password: String,
        birthDate: String,
        gender: String,
        profession: String,
        occupation: String,
        maritalStatus: String,
        livingArea: String,
        publicFigure: String
    ) : Result<Unit>

    fun firebaseLogIn(emailAddress: String, password: String) : Result<Unit>

    fun firebaseLogOut()
}
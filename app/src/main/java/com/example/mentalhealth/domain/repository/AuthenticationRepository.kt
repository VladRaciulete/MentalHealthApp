package com.example.mentalhealth.domain.repository

import com.example.mentalhealth.domain.model.User

interface AuthenticationRepository {
    fun isUserAuthenticatedInFirebase(): Boolean

    suspend fun firebaseSignUp(emailAddress: String, password: String, user: User): Result<Unit>

    suspend fun firebaseLogIn(emailAddress: String, password: String): Result<Unit>

    suspend fun firebaseLogOut(): Result<Unit>
}
package com.example.mentalhealth.data.repository

import com.example.mentalhealth.data.datasource.FirestoreDataSource
import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val dataSource: FirestoreDataSource
) : AuthenticationRepository {

    override fun isUserAuthenticatedInFirebase(): Boolean {
        return dataSource.isUserAuthenticatedInFirebase()
    }

    override suspend fun firebaseSignUp(
        emailAddress: String,
        password: String,
        user: User
    ): Result<Unit> {
        return dataSource.firebaseSignUp(emailAddress, password, user)
    }

    override suspend fun firebaseLogIn(emailAddress: String, password: String): Result<Unit> {
        return dataSource.firebaseLogIn(emailAddress, password)
    }

    override suspend fun firebaseLogOut(): Result<Unit> {
        return dataSource.firebaseLogOut()
    }
}
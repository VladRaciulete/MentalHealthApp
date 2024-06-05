package com.example.mentalhealth.data.repository

import com.example.mentalhealth.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth : FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthenticationRepository {

    override fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    suspend override fun firebaseSignUp(
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
    ) : Result<Unit> {
        return try {
            val signUpResult = auth.createUserWithEmailAndPassword(emailAddress, password).await()
            val user = signUpResult.user ?: throw Exception("Sign Up failed!")

            val userId = user.uid
            val userData = hashMapOf(
                "firstName" to firstName,
                "lastName" to lastName,
                "birthDate" to birthDate,
                "gender" to gender,
                "profession" to profession,
                "occupation" to occupation,
                "maritalStatus" to maritalStatus,
                "livingArea" to livingArea,
                "publicFigure" to publicFigure
            )

            firestore.collection("users").document(userId).set(userData).await()
            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun firebaseLogIn(emailAddress: String, password: String) : Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(emailAddress, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun firebaseLogOut() {
        auth.signOut()
    }
}
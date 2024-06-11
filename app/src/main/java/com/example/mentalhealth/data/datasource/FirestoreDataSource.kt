package com.example.mentalhealth.data.datasource

import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreDataSource @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun firebaseSignUp(
        emailAddress: String,
        password: String,
        userData: User
    ): Result<Unit> {
        return try {
            val signUpResult = auth.createUserWithEmailAndPassword(emailAddress, password).await()
            val user = signUpResult.user ?: throw Exception("Sign Up failed!")

            val userId = user.uid
            firestore
                .collection("users")
                .document(userId)
                .set(userData)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun firebaseLogIn(emailAddress: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(emailAddress, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun firebaseLogOut(): Result<Unit> {
        return try {
            auth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    suspend fun loadUserDataFromFireStore(): User? {
        return try {
            val currentUser = auth.currentUser

            if (currentUser != null) {
                val userData = firestore
                    .collection("users")
                    .document(currentUser.uid)
                    .get()
                    .await()

                userData.toObject(User::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getJournalEntry(date: String): DailyJournal? {
        val currentUser = auth.currentUser

        return if (currentUser != null) {
            try {
                val dailyJournal = firestore.collection("users")
                    .document(currentUser.uid)
                    .collection("dailyJournal")
                    .document(date)
                    .get()
                    .await()

                if (dailyJournal.exists()) {
                    val dailyJournalEntry = dailyJournal.toObject(DailyJournal::class.java)
                    dailyJournalEntry
                } else {
                    null
                }

            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }

    suspend fun addMorningJournalEntry(journalData: DailyJournal, date: String): Result<Unit> {
        val currentUser = auth.currentUser

        return if (currentUser != null) {
            try {
                firestore.collection("users")
                    .document(currentUser.uid)
                    .collection("dailyJournal")
                    .document(date)
                    .set(journalData)
                    .await()

                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.failure(Exception("Current user is null"))
        }
    }

    suspend fun addEveningJournalEntry(journalData: DailyJournal, date: String): Result<Unit> {
        val currentUser = auth.currentUser

        return if (currentUser != null) {
            try {
                firestore.collection("users")
                    .document(currentUser.uid)
                    .collection("dailyJournal")
                    .document(date)
                    .set(journalData, SetOptions.merge())
                    .await()

                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.failure(Exception("Current user is null"))
        }
    }


}
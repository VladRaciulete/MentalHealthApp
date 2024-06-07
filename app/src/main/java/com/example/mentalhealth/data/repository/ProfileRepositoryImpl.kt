package com.example.mentalhealth.data.repository

import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ProfileRepository {
    override suspend fun loadUserDataFromFireStore(): User? {
        return try {
            val currentUser = auth.currentUser

            if (currentUser != null) {
                val userData = firestore.collection("users").document(currentUser.uid).get().await()
                userData.toObject(User::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
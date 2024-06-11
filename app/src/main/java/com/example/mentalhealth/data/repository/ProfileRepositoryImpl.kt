package com.example.mentalhealth.data.repository

import com.example.mentalhealth.data.datasource.FirestoreDataSource
import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dataSource: FirestoreDataSource
) : ProfileRepository {
    override suspend fun loadUserDataFromFireStore(): User? {
        return dataSource.loadUserDataFromFireStore()
    }
}
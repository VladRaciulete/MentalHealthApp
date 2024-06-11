package com.example.mentalhealth.data.datasource

import com.example.mentalhealth.domain.model.DailyJournal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreDataSource @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
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

                if(dailyJournal.exists()){
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

    suspend fun addEveningJournalEntry(journalData: DailyJournal, date: String): Result<Unit>{
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
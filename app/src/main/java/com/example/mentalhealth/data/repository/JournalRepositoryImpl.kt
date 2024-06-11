package com.example.mentalhealth.data.repository

import com.example.mentalhealth.data.datasource.FirestoreDataSource
import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.repository.JournalRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class JournalRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val dataSource: FirestoreDataSource
) : JournalRepository {
    override suspend fun getJournalEntry(date: String): DailyJournal? {
        return dataSource.getJournalEntry(date)
    }

    override suspend fun addMorningJournalEntry(journalData: DailyJournal, date: String): Result<Unit> {
        return dataSource.addMorningJournalEntry(journalData, date)
    }

    override suspend fun addEveningJournalEntry(journalData: DailyJournal, date: String): Result<Unit> {
        return dataSource.addEveningJournalEntry(journalData, date)
    }
}
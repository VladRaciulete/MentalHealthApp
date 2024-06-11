package com.example.mentalhealth.data.repository

import com.example.mentalhealth.data.datasource.FirestoreDataSource
import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.repository.JournalRepository
import javax.inject.Inject

class JournalRepositoryImpl @Inject constructor(
    private val dataSource: FirestoreDataSource
) : JournalRepository {
    override suspend fun getJournalEntry(date: String): DailyJournal? {
        return dataSource.getJournalEntry(date)
    }

    override suspend fun addMorningJournalEntry(
        journalData: DailyJournal,
        date: String
    ): Result<Unit> {
        return dataSource.addMorningJournalEntry(journalData, date)
    }

    override suspend fun addEveningJournalEntry(
        journalData: DailyJournal,
        date: String
    ): Result<Unit> {
        return dataSource.addEveningJournalEntry(journalData, date)
    }
}
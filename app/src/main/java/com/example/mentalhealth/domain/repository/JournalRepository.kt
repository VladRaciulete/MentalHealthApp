package com.example.mentalhealth.domain.repository

import com.example.mentalhealth.domain.model.DailyJournal

interface JournalRepository {
    suspend fun getJournalEntry(date: String) : DailyJournal?
    suspend fun addMorningJournalEntry(journalData: DailyJournal, date: String) : Result<Unit>
    suspend fun addEveningJournalEntry(journalData: DailyJournal, date: String) : Result<Unit>
}
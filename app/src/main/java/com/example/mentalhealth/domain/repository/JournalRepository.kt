package com.example.mentalhealth.domain.repository

import android.content.Context
import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.model.MLInput

interface JournalRepository {
    suspend fun getJournalEntry(date: String): DailyJournal?
    suspend fun addMorningJournalEntry(journalData: DailyJournal, date: String): Result<Unit>
    suspend fun addEveningJournalEntry(journalData: DailyJournal, date: String): Result<Unit>
    suspend fun machineLearningPrediction(mlInput: MLInput, date: String): Result<Unit>
}
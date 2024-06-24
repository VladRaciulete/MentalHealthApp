package com.example.mentalhealth.domain.repository

import com.example.mentalhealth.domain.model.DailyJournal

interface StatisticsRepository {
    suspend fun getJournalEntriesFromDateRange(
        startDate: String,
        endDate: String
    ): List<DailyJournal>?
}

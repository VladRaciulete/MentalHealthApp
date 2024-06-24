package com.example.mentalhealth.data.repository

import com.example.mentalhealth.data.datasource.FirestoreDataSource
import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.repository.StatisticsRepository
import javax.inject.Inject

class StatisticsRepositoryImpl @Inject constructor(
    private val dataSource: FirestoreDataSource
) : StatisticsRepository {
    override suspend fun getJournalEntriesFromDateRange(
        startDate: String,
        endDate: String
    ): List<DailyJournal>? {
        return dataSource.getJournalEntriesFromDateRange(startDate, endDate)
    }
}

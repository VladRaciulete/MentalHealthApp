package com.example.mentalhealth.domain.usecase.statistics

import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.repository.StatisticsRepository

class GetJournalEntriesFromDateRangeUseCase(private val statisticsRepository: StatisticsRepository) {
    suspend operator fun invoke(startDate: String, endDate: String): List<DailyJournal>? {
        return statisticsRepository.getJournalEntriesFromDateRange(startDate, endDate)
    }
}

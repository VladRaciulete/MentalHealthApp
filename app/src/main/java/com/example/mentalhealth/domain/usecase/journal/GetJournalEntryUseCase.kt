package com.example.mentalhealth.domain.usecase.journal

import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.repository.JournalRepository

class GetJournalEntryUseCase(private val journalRepository: JournalRepository) {
    suspend operator fun invoke(date: String): DailyJournal? {
        return journalRepository.getJournalEntry(date)
    }
}
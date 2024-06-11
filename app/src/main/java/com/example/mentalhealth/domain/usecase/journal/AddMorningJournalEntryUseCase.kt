package com.example.mentalhealth.domain.usecase.journal

import com.example.mentalhealth.domain.model.DailyJournal
import com.example.mentalhealth.domain.repository.JournalRepository

class AddMorningJournalEntryUseCase(private val journalRepository: JournalRepository) {
    suspend operator fun invoke(journalData: DailyJournal, date: String): Result<Unit> {
        return journalRepository.addMorningJournalEntry(journalData, date)
    }
}
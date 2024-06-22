package com.example.mentalhealth.domain.usecase.journal

import com.example.mentalhealth.domain.model.MLInput
import com.example.mentalhealth.domain.repository.JournalRepository

class MLPredictionUseCase(private val journalRepository: JournalRepository) {
    suspend operator fun invoke(mlInput: MLInput, date: String): Result<Unit> {
        return journalRepository.machineLearningPrediction(mlInput, date)
    }
}

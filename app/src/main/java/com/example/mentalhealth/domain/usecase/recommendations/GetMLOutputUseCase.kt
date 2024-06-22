package com.example.mentalhealth.domain.usecase.recommendations

import com.example.mentalhealth.domain.model.MLOutput
import com.example.mentalhealth.domain.repository.RecommendationsRepository

class GetMLOutputUseCase(private val recommendationsRepository: RecommendationsRepository) {
    suspend operator fun invoke(date: String): MLOutput? {
        return recommendationsRepository.getMLOutput(date)
    }
}

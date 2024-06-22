package com.example.mentalhealth.domain.repository

import com.example.mentalhealth.domain.model.MLOutput

interface RecommendationsRepository {
    suspend fun getMLOutput(date: String): MLOutput?
}

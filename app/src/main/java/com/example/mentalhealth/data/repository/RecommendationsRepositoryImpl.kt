package com.example.mentalhealth.data.repository

import com.example.mentalhealth.data.datasource.FirestoreDataSource
import com.example.mentalhealth.domain.model.MLOutput
import com.example.mentalhealth.domain.repository.RecommendationsRepository
import javax.inject.Inject

class RecommendationsRepositoryImpl @Inject constructor(
    private val dataSource: FirestoreDataSource
): RecommendationsRepository {
    override suspend fun getMLOutput(date: String): MLOutput? {
        return dataSource.getMLOutput(date)
    }
}

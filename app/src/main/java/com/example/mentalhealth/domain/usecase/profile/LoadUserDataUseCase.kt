package com.example.mentalhealth.domain.usecase.profile

import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.domain.repository.ProfileRepository
import com.google.android.gms.common.api.Response
import kotlinx.coroutines.flow.Flow

class LoadUserDataUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(): User? {
        return profileRepository.loadUserDataFromFireStore()
    }
}
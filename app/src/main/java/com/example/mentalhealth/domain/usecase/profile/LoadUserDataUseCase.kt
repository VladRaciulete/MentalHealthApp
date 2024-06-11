package com.example.mentalhealth.domain.usecase.profile

import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.domain.repository.ProfileRepository

class LoadUserDataUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(): User? {
        return profileRepository.loadUserDataFromFireStore()
    }
}
package com.example.mentalhealth.domain.usecase.profile

import com.example.mentalhealth.domain.model.User
import com.example.mentalhealth.domain.repository.ProfileRepository

class UpdateUserDataUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(user: User): Result<Unit> {
        return profileRepository.updateUserData(user)
    }
}

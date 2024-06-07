package com.example.mentalhealth.domain.repository

import com.example.mentalhealth.domain.model.User

interface ProfileRepository  {
    suspend fun loadUserDataFromFireStore(): User?
}
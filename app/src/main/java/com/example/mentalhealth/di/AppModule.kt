package com.example.mentalhealth.di

import com.example.mentalhealth.data.repository.AuthenticationRepositoryImpl
import com.example.mentalhealth.domain.repository.AuthenticationRepository
import com.example.mentalhealth.domain.usecase.auth.CheckUserAuthenticatedUseCase
import com.example.mentalhealth.domain.usecase.auth.LogInUseCase
import com.example.mentalhealth.domain.usecase.account.LogOutUseCase
import com.example.mentalhealth.domain.usecase.auth.SignUpUseCase
import com.example.mentalhealth.presentation.AppStateViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestoreInstance() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ) : AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth, firestore)
    }

    @Provides
    @Singleton
    fun provideLogInUseCase(authRepository: AuthenticationRepository) : LogInUseCase {
        return LogInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthenticationRepository) : SignUpUseCase {
        return SignUpUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideLogOutUseCase(authRepository: AuthenticationRepository) : LogOutUseCase {
        return LogOutUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideCheckUserAuthenticatedUseCase(authRepository: AuthenticationRepository) : CheckUserAuthenticatedUseCase {
        return CheckUserAuthenticatedUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideAppStateViewModel() : AppStateViewModel {
        return AppStateViewModel()
    }
}
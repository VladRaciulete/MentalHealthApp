package com.example.mentalhealth.di

import com.example.mentalhealth.data.datasource.FirestoreDataSource
import com.example.mentalhealth.data.repository.AuthenticationRepositoryImpl
import com.example.mentalhealth.data.repository.JournalRepositoryImpl
import com.example.mentalhealth.data.repository.ProfileRepositoryImpl
import com.example.mentalhealth.domain.repository.AuthenticationRepository
import com.example.mentalhealth.domain.repository.JournalRepository
import com.example.mentalhealth.domain.repository.ProfileRepository
import com.example.mentalhealth.domain.usecase.auth.CheckUserAuthenticatedUseCase
import com.example.mentalhealth.domain.usecase.auth.LogInUseCase
import com.example.mentalhealth.domain.usecase.profile.LogOutUseCase
import com.example.mentalhealth.domain.usecase.auth.SignUpUseCase
import com.example.mentalhealth.domain.usecase.journal.AddEveningJournalEntryUseCase
import com.example.mentalhealth.domain.usecase.journal.AddMorningJournalEntryUseCase
import com.example.mentalhealth.domain.usecase.journal.GetJournalEntryUseCase
import com.example.mentalhealth.domain.usecase.profile.LoadUserDataUseCase
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
    fun provideFirebaseFireStoreInstance() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirestoreDataSource(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): FirestoreDataSource {
        return FirestoreDataSource(auth, firestore)
    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        dataSource: FirestoreDataSource
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideJournalRepository(
        dataSource: FirestoreDataSource
    ): JournalRepository {
        return JournalRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        dataSource: FirestoreDataSource
    ): ProfileRepository {
        return ProfileRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideLogInUseCase(authRepository: AuthenticationRepository): LogInUseCase {
        return LogInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthenticationRepository): SignUpUseCase {
        return SignUpUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideLogOutUseCase(authRepository: AuthenticationRepository): LogOutUseCase {
        return LogOutUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideLoadUserDataUseCase(profileRepository: ProfileRepository): LoadUserDataUseCase {
        return LoadUserDataUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideCheckUserAuthenticatedUseCase(authRepository: AuthenticationRepository): CheckUserAuthenticatedUseCase {
        return CheckUserAuthenticatedUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideAppStateViewModel(): AppStateViewModel {
        return AppStateViewModel()
    }

    @Provides
    @Singleton
    fun provideAddEveningJournalUseCase(journalRepository: JournalRepository): AddEveningJournalEntryUseCase {
        return AddEveningJournalEntryUseCase(journalRepository)
    }

    @Provides
    @Singleton
    fun provideAddMorningJournalUseCase(journalRepository: JournalRepository): AddMorningJournalEntryUseCase {
        return AddMorningJournalEntryUseCase(journalRepository)
    }

    @Provides
    @Singleton
    fun provideGetJournalEntryUseCase(journalRepository: JournalRepository): GetJournalEntryUseCase {
        return GetJournalEntryUseCase(journalRepository)
    }


}
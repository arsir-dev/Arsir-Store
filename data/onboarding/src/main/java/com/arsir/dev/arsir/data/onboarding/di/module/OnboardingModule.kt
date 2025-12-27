package com.arsir.dev.arsir.data.onboarding.di.module

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.arsir.dev.arsir.core.coroutines.qualifier.IoDispatcher
import com.arsir.dev.arsir.core.datastore.qualifier.AppDataStore
import com.arsir.dev.arsir.data.onboarding.implementation.datastore.OnboardingDataStore
import com.arsir.dev.arsir.data.onboarding.implementation.datastore.OnboardingDataStoreImpl
import com.arsir.dev.arsir.data.onboarding.implementation.repository.OnboardingRepositoryImpl
import com.arsir.dev.arsir.domain.onboarding.repository.OnboardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object OnboardingModule {
    @Provides
    @Singleton
    fun provideOnboardingDataStore(
        @AppDataStore dataStore: DataStore<Preferences>,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): OnboardingDataStore {
        return OnboardingDataStoreImpl(
            dataStore = dataStore,
            ioDispatcher = ioDispatcher,
        )
    }
    @Provides
    fun provideOnboardingRepository(
        onboardingDataStore: OnboardingDataStore,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): OnboardingRepository {
        return OnboardingRepositoryImpl(
            onboardingDataStore = onboardingDataStore,
            ioDispatcher = ioDispatcher,
        )
    }
}
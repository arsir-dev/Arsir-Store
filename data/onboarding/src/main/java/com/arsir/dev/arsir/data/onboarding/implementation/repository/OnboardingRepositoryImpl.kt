package com.arsir.dev.arsir.data.onboarding.implementation.repository

import com.arsir.dev.arsir.data.onboarding.implementation.datastore.OnboardingDataStore
import com.arsir.dev.arsir.domain.onboarding.repository.OnboardingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingDataStore: OnboardingDataStore,
    private val ioDispatcher: CoroutineDispatcher
): OnboardingRepository {
    override suspend fun setRunOnce() = withContext(ioDispatcher) {
        onboardingDataStore.setRunOnce()
    }
}
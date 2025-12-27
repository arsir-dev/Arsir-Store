package com.arsir.dev.arsir.data.splash.screen.implementation.repository

import com.arsir.dev.arsir.data.splash.screen.implementation.datastore.SplashDataStore
import com.arsir.dev.arsir.domain.splash.screen.repository.SplashRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SplashRepositoryImpl @Inject constructor(
    private val splashDataStore: SplashDataStore
): SplashRepository {
    override suspend fun getRunOnce(): Flow<Boolean> {
        return splashDataStore.getRunOnce()
    }
}
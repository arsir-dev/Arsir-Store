package com.arsir.dev.arsir.domain.splash.screen.repository

import kotlinx.coroutines.flow.Flow

fun interface SplashRepository {
    suspend fun getRunOnce(): Flow<Boolean>
}
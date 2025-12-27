package com.arsir.dev.arsir.data.splash.screen.implementation.datastore

import kotlinx.coroutines.flow.Flow

fun interface SplashDataStore {
    suspend fun getRunOnce(): Flow<Boolean>
}
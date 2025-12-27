package com.arsir.dev.arsir.data.splash.screen.implementation.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class SplashDataStoreImpl(
    private val dataStore: DataStore<Preferences>,
    private val ioDispatcher: CoroutineDispatcher,
): SplashDataStore {
    override suspend fun getRunOnce(): Flow<Boolean> = withContext(ioDispatcher) {
        return@withContext dataStore.data.map { preference ->
            preference[KEY_RUN_ONCE] ?: false
        }
    }

    private companion object {
        val KEY_RUN_ONCE = booleanPreferencesKey("key_run_once")
    }
}
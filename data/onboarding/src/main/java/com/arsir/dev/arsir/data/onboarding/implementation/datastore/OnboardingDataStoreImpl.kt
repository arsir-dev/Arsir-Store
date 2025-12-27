package com.arsir.dev.arsir.data.onboarding.implementation.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnboardingDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val ioDispatcher: CoroutineDispatcher,
): OnboardingDataStore {
    override suspend fun setRunOnce(): Unit = withContext(ioDispatcher) {
        dataStore.edit { it[KEY_RUN_ONCE] = true }
    }

    private companion object {
        val KEY_RUN_ONCE = booleanPreferencesKey("key_run_once")
    }
}
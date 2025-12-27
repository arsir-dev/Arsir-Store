package com.arsir.dev.arsir.data.login.implementation.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LoginDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val ioDispatcher: CoroutineDispatcher,
): LoginDataStore {

    override suspend fun setToken(token: String): Unit = withContext(ioDispatcher) {
        dataStore.edit { it[KEY_TOKEN] = token }
    }

    private companion object {
        val KEY_TOKEN = stringPreferencesKey(name = "key_token")
    }
}
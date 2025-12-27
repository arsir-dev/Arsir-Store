package com.arsir.dev.arsir.data.login.implementation.datastore

internal fun interface LoginDataStore {
    suspend fun setToken(token: String)
}
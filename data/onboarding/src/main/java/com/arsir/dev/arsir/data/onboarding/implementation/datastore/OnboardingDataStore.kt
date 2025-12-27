package com.arsir.dev.arsir.data.onboarding.implementation.datastore

fun interface OnboardingDataStore {
    suspend fun setRunOnce()
}
package com.arsir.dev.arsir.domain.onboarding.repository

fun interface OnboardingRepository {
    suspend fun setRunOnce()
}
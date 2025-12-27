package com.arsir.dev.arsir.feature.onboarding.di.module

import com.arsir.dev.arsir.feature.onboarding.navigation.OnboardingNavigationImpl
import com.arsir.dev.arsir.navigation.OnboardingNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface OnboardingScreenModule {
    @Binds
    fun bindOnboardingNavigation(
        onboardingNavigationImpl: OnboardingNavigationImpl
    ): OnboardingNavigation
}
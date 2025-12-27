package com.arsir.dev.arsir.feature.splash.screen.di.module

import com.arsir.dev.arsir.feature.splash.screen.navigation.SplashNavigationImpl
import com.arsir.dev.arsir.navigation.SplashNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SplashScreenModule {
    @Binds
    fun bindSplashNavigation(
        splashNavigationImpl: SplashNavigationImpl
    ): SplashNavigation
}
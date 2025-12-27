package com.arsir.dev.arsir.feature.splash.screen.screen

sealed class SplashEffect {
    data object Onboarding : SplashEffect()
    data object Login : SplashEffect()
}
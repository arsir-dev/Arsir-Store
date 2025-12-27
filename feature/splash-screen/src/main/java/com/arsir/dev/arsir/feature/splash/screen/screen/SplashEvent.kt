package com.arsir.dev.arsir.feature.splash.screen.screen

import com.arsir.dev.arsir.uikit.event.UiEvent

sealed class SplashEvent : UiEvent {
    data object OnLoad : SplashEvent()
}
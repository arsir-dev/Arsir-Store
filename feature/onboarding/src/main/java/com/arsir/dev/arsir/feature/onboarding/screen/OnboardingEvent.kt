package com.arsir.dev.arsir.feature.onboarding.screen

import com.arsir.dev.arsir.uikit.event.UiEvent

sealed interface OnboardingEvent : UiEvent {
    data object OnFinish : OnboardingEvent
}
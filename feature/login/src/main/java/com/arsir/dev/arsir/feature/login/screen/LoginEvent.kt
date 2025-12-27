package com.arsir.dev.arsir.feature.login.screen

import com.arsir.dev.arsir.uikit.event.UiEvent

sealed class LoginEvent : UiEvent {
    data class OnUsername(val username: String) : LoginEvent()
    data class OnPassword(val password: String) : LoginEvent()
    data class OnLogin(
        val username: String,
        val password: String,
    ) : LoginEvent()
}
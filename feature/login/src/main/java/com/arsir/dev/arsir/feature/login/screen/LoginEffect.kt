package com.arsir.dev.arsir.feature.login.screen

sealed class LoginEffect {
    data object OnHome : LoginEffect()
    data class OnError(val message: String) : LoginEffect()
}
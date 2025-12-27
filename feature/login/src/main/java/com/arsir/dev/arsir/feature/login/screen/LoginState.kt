package com.arsir.dev.arsir.feature.login.screen

import androidx.compose.runtime.Stable

@Stable
data class LoginState(
    val username: String = "",
    val password: String = "",
    val usernameError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false
) {
    val isUsernameBlank = username.isBlank()
    val isPasswordBlank = password.isBlank()
}

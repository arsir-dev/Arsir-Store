package com.arsir.dev.arsir.data.login.implementation.mapper

import com.arsir.dev.arsir.data.login.implementation.remote.response.LoginResponse
import com.arsir.dev.arsir.domain.login.model.Login

internal fun LoginResponse.toLogin(): Login {
    return Login(
        token = token.orEmpty()
    )
}
package com.arsir.dev.arsir.data.login.implementation.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class LoginResponse(
    @Json(name = "token")
    val token: String? = null
)
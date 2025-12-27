package com.arsir.dev.arsir.data.login.implementation.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class LoginRequest(
    @Json(name = "username")
    val username: String = "",

    @Json(name = "password")
    val password: String = "",
)
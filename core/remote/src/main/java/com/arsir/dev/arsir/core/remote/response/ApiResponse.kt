package com.arsir.dev.arsir.core.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    @Json(name = "data")
    val data: T? = null,
    @Json(name = "code")
    val code: String? = null,
    @Json(name = "message")
    val message: String? = null,
)

package com.arsir.dev.arsir.data.product.implementation.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class RatingResponse(
    @Json(name = "rate")
    val rate: Double? = null,

    @Json(name = "count")
    val count: Int? = null
)
package com.arsir.dev.arsir.data.product.implementation.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ProductResponse(
    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "price")
    val price: Double? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "category")
    val category: String? = null,

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "rating")
    val rating: RatingResponse
)
package com.arsir.dev.arsir.domain.product.model

data class Cart(
    val id: Int = 0,
    val title: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val category: String = "",
    val image: String = "",
    val quantity: Int = 0,
    val isFavorite: Boolean = false,
    val rating: Rating = Rating(),
)
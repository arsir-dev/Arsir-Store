package com.arsir.dev.arsir.feature.home.screen.cart

import androidx.compose.runtime.Stable
import com.arsir.dev.arsir.domain.product.model.Cart

@Stable
data class CartState(
    val isLoading: Boolean = false,
    val isShimmer: Boolean = false,
    val query: String = "",
    val price: Double = 0.0,
    val cart: List<Cart> = emptyList(),
    val carts: List<Cart> = emptyList(),
    val favoriteId: Set<Int>? = setOf(),
)
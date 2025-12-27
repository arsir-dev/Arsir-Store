package com.arsir.dev.arsir.feature.detail.screen

import androidx.compose.runtime.Stable
import com.arsir.dev.arsir.domain.product.model.Product

@Stable
data class DetailState(
    val isLoading: Boolean = false,
    val isShimmer: Boolean = false,
    val product: Product = Product(),
    val quantity: Int = 0,
) {
    val totalPrice: Double
        get() = product.price * quantity
}
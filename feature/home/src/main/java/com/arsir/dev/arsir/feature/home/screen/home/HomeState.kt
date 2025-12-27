package com.arsir.dev.arsir.feature.home.screen.home

import androidx.compose.runtime.Stable
import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.feature.home.ext.Category

@Stable
data class HomeState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isShimmerPopular: Boolean = false,
    val product: List<Product> = emptyList(),
    val banner: List<Product> = emptyList(),
    val category: List<Category?> = emptyList(),
    val popular: List<Product> = emptyList(),
    val favoriteId: Set<Int> = setOf(),
)

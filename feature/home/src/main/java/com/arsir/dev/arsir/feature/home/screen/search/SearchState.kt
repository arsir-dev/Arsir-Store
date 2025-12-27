package com.arsir.dev.arsir.feature.home.screen.search

import androidx.compose.runtime.Stable
import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.feature.home.ext.Category

@Stable
data class SearchState(
    val query: String = "",
    val isLoading: Boolean = false,
    val isShimmer: Boolean = false,
    val product: List<Product> = emptyList(),
    val products: List<Product> = emptyList(),
    val favoriteId: Set<Int> = setOf(),
    val category: List<Category?> = emptyList()
)
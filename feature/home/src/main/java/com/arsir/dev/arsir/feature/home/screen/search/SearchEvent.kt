package com.arsir.dev.arsir.feature.home.screen.search

import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.uikit.event.UiEvent

sealed class SearchEvent : UiEvent {
    data object OnFetching : SearchEvent()
    data class OnDetail(val productId: Int) : SearchEvent()
    data class OnValueChange(val query: String) : SearchEvent()
    data class OnCategory(val category: String) : SearchEvent()
    data class OnFavorite(val product: Product) : SearchEvent()
}
package com.arsir.dev.arsir.feature.home.screen.home

import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.uikit.event.UiEvent

sealed class HomeEvent: UiEvent {
    data object OnSearch : HomeEvent()
    data object OnFetching : HomeEvent()
    data object OnProfile : HomeEvent()
    data object OnRefresh : HomeEvent()
    data object OnSeeAllCategory : HomeEvent()
    data class OnDetail(val productId: Int) : HomeEvent()
    data class OnCategory(val category: String) : HomeEvent()
    data class OnFavorite(val product: Product) : HomeEvent()
}

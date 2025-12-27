package com.arsir.dev.arsir.feature.home.screen.search

sealed class SearchEffect {
    data class OnDetail(val productId: Int) : SearchEffect()
    data class OnGeneralError(val message: String) : SearchEffect()
}
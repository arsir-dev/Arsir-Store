package com.arsir.dev.arsir.feature.home.screen.home

sealed class HomeEffect {
    data object OnSearch : HomeEffect()
    data object OnProfile : HomeEffect()
    data object OnSeeAllCategory : HomeEffect()
    data class OnDetail(val productId: Int) : HomeEffect()
    data class OnGeneralError(val message: String) : HomeEffect()
}
package com.arsir.dev.arsir.feature.home.screen.cart

sealed interface CartEffect {
    data object OnBack : CartEffect
    data class OnCheckout(val subTotal: Long) : CartEffect
    data class OnSearch(val query: String) : CartEffect
    data class OnGeneralError(val message: String) : CartEffect
}
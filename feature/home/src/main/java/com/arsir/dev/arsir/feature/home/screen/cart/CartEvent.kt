package com.arsir.dev.arsir.feature.home.screen.cart

import com.arsir.dev.arsir.domain.product.model.Cart
import com.arsir.dev.arsir.uikit.event.UiEvent

sealed interface CartEvent : UiEvent {
    data object OnBack : CartEvent
    data class OnCheckout(val subTotal: Long) : CartEvent
    data class OnRemove(val productId: Int) : CartEvent
    data class OnIncrease(val productId: Int) : CartEvent
    data class OnDecrease(val productId: Int) : CartEvent
    data class OnValueChange(val query: String) : CartEvent
    data class OnFavorite(val cart: Cart) : CartEvent
}
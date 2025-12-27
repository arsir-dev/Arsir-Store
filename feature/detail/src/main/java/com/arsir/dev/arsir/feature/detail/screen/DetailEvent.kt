package com.arsir.dev.arsir.feature.detail.screen

import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.uikit.event.UiEvent

sealed interface DetailEvent : UiEvent {
    data object OnBack : DetailEvent
    data object OnIncrease : DetailEvent
    data object OnDecrease : DetailEvent
    data class OnUpsert(val product: Product) : DetailEvent
}
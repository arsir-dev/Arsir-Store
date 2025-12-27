package com.arsir.dev.arsir.feature.home.screen.cart

import com.arsir.dev.arsir.uikit.modal.ModalType

sealed interface CartModalState {
    data object Hidden : CartModalState
    data class Shown(val type: ModalType, val message: String) : CartModalState
}
package com.arsir.dev.arsir.feature.home.screen.home

import com.arsir.dev.arsir.uikit.modal.ModalType

sealed interface HomeModalState {
    data object Hidden : HomeModalState
    data class Shown(val type: ModalType, val message: String) : HomeModalState
}
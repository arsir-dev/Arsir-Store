package com.arsir.dev.arsir.feature.home.screen.search

import com.arsir.dev.arsir.uikit.modal.ModalType

sealed interface SearchModalState {
    data object Hidden : SearchModalState
    data class Shown(val type: ModalType, val message: String) : SearchModalState
}
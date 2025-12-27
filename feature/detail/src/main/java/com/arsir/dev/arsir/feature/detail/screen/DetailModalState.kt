package com.arsir.dev.arsir.feature.detail.screen

import com.arsir.dev.arsir.uikit.modal.ModalType

sealed interface DetailModalState {
    data object Hidden : DetailModalState
    data class Shown(val type: ModalType, val message: String? = "") : DetailModalState
}
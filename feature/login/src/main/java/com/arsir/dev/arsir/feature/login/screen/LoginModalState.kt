package com.arsir.dev.arsir.feature.login.screen

import com.arsir.dev.arsir.uikit.modal.ModalType

sealed interface LoginModalState {
    data object Hidden : LoginModalState
    data class Shown(val type: ModalType, val message: String) : LoginModalState
}
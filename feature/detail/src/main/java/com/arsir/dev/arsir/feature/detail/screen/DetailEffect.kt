package com.arsir.dev.arsir.feature.detail.screen

sealed interface DetailEffect {
    data object OnBack : DetailEffect
    data object OnSuccess : DetailEffect
    data class OnLoading(val isLoading: Boolean) : DetailEffect
    data class OnGeneralError(val message: String) : DetailEffect
}

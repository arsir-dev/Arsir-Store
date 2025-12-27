package com.arsir.dev.arsir.uikit.button

import androidx.compose.runtime.Stable

@Stable
sealed interface ButtonAppearance {
    data object Primary : ButtonAppearance
    data object Outline : ButtonAppearance
}
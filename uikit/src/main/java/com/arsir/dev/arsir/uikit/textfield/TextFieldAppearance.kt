package com.arsir.dev.arsir.uikit.textfield

import androidx.compose.runtime.Stable

@Stable
sealed interface TextFieldAppearance {
    data object Normal : TextFieldAppearance
    data object Password : TextFieldAppearance
    data object Search : TextFieldAppearance
}
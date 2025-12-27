package com.arsir.dev.arsir.uikit.bottomsheet

import androidx.compose.animation.core.TweenSpec
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.navigation.BottomSheetNavigator
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberBottomSheetNavigator(
    initialValue: ModalBottomSheetValue = ModalBottomSheetValue.Hidden,
    confirmValueChange: (ModalBottomSheetValue) -> Boolean = { true },
    skipHalfExpanded: Boolean = false,
): BottomSheetNavigator {
    val sheetState = rememberModalBottomSheetState(
        initialValue = initialValue,
        animationSpec = TweenSpec(durationMillis = 300),
        confirmValueChange = confirmValueChange,
        skipHalfExpanded = skipHalfExpanded
    )
    return remember { BottomSheetNavigator(sheetState) }
}
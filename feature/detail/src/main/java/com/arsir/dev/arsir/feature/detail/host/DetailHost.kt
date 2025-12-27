package com.arsir.dev.arsir.feature.detail.host

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arsir.dev.arsir.feature.detail.screen.DetailDestination
import com.arsir.dev.arsir.feature.detail.screen.detailScreen
import com.arsir.dev.arsir.uikit.bottomsheet.BottomSheetShape
import com.arsir.dev.arsir.uikit.bottomsheet.rememberBottomSheetNavigator

@Composable
internal fun DetailHost(productId: Int) {
    val bottomSheetNavigator = rememberBottomSheetNavigator(skipHalfExpanded = true)
    val navController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = BottomSheetShape,
        scrimColor = Color.Black.copy(alpha = 0.4f)
    ) {
        NavHost(
            modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            navController = navController,
            startDestination = DetailDestination(productId = productId),
        ) {
            detailScreen()
        }
    }
}
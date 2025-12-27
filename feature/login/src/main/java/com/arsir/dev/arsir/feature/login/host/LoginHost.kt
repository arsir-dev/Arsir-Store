package com.arsir.dev.arsir.feature.login.host

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arsir.dev.arsir.feature.login.screen.LoginNavigation
import com.arsir.dev.arsir.feature.login.screen.loginScreen
import com.arsir.dev.arsir.uikit.bottomsheet.BottomSheetShape
import com.arsir.dev.arsir.uikit.bottomsheet.rememberBottomSheetNavigator

@Composable
internal fun LoginHost() {
    val bottomSheetNavigator = rememberBottomSheetNavigator(skipHalfExpanded = true)
    val navController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = BottomSheetShape,
        scrimColor = MaterialTheme.colors.background
    ) {
        NavHost(
            modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            navController = navController,
            startDestination = LoginNavigation,
        ) {
            loginScreen()
        }
    }
}
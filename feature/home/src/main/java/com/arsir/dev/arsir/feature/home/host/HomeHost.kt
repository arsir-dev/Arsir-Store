package com.arsir.dev.arsir.feature.home.host

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arsir.dev.arsir.feature.home.component.BottomNavigationBar
import com.arsir.dev.arsir.feature.home.screen.cart.cartScreen
import com.arsir.dev.arsir.feature.home.screen.checkout.checkoutScreen
import com.arsir.dev.arsir.feature.home.screen.home.HomeDestination
import com.arsir.dev.arsir.feature.home.screen.home.homeScreen
import com.arsir.dev.arsir.feature.home.screen.profile.profileScreen
import com.arsir.dev.arsir.feature.home.screen.search.searchScreen
import com.arsir.dev.arsir.uikit.bottomsheet.BottomSheetShape
import com.arsir.dev.arsir.uikit.bottomsheet.rememberBottomSheetNavigator

@Composable
internal fun HomeHost() {
    val bottomSheetNavigator = rememberBottomSheetNavigator(skipHalfExpanded = true)
    val navController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = BottomSheetShape,
        scrimColor = Color.Black.copy(alpha = 0.4f)
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) { paddingValues ->
            NavHost(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .windowInsetsPadding(WindowInsets.systemBars),
                navController = navController,
                startDestination = HomeDestination,
            ) {
                homeScreen(navController = navController)
                cartScreen(navController = navController)
                profileScreen(navController = navController)
                searchScreen(navController = navController)
                checkoutScreen(navController = navController)
            }
        }
    }
}
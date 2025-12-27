package com.arsir.dev.arsir.feature.home.screen.profile

import androidx.activity.compose.BackHandler
import androidx.compose.material.navigation.bottomSheet
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.arsir.dev.arsir.uikit.screen.Screen
import kotlinx.serialization.Serializable

@Serializable
internal data object ProfileDestination : Screen

internal fun NavGraphBuilder.profileScreen(navController: NavHostController) {
    bottomSheet<ProfileDestination> {
        BackHandler {
            navController.navigateUp()
        }

        ProfileScreen {
            navController.navigateUp()
        }
    }
}
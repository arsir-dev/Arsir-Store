package com.arsir.dev.arsir.feature.home.screen.checkout

import androidx.activity.compose.BackHandler
import androidx.compose.material.navigation.bottomSheet
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import com.arsir.dev.arsir.uikit.screen.Screen
import kotlinx.serialization.Serializable

@Serializable
internal data class CheckoutDestination(val subTotal: Long) : Screen

internal fun NavGraphBuilder.checkoutScreen(navController: NavHostController) {
    bottomSheet<CheckoutDestination> { backStackeEntry ->
        val args = backStackeEntry.toRoute<CheckoutDestination>()

        BackHandler {
            navController.navigateUp()
        }

        CheckoutScreen(
            subTotal = args.subTotal.toDouble()
        ) { navController.navigateUp() }
    }
}
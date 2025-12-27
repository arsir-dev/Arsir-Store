package com.arsir.dev.arsir.feature.home.component

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arsir.dev.arsir.feature.home.event.BottomNavigationBarEvent
import com.arsir.dev.arsir.feature.home.screen.cart.CartDestination
import com.arsir.dev.arsir.feature.home.screen.home.HomeDestination
import com.arsir.dev.arsir.feature.home.screen.search.SearchDestination
import com.arsir.dev.arsir.uikit.screen.Screen
import com.arsir.dev.arsir.uikit.theme.Typography
import kotlin.reflect.KClass
import com.arsir.dev.arsir.uikit.R as RUIKit

@Composable
internal fun BottomNavigationBar(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val navigationItems = listOf(
        Triple("Home", RUIKit.drawable.ic_home, HomeDestination),
        Triple("Search & Category", RUIKit.drawable.ic_category, SearchDestination),
        Triple("Cart", RUIKit.drawable.ic_cart, CartDestination),
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        navigationItems.forEach { (name, icon, destination) ->
            val isSelected = currentDestination?.hasRoute(destination::class) == true
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(destination) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(painter = painterResource(icon), contentDescription = name)
                },
                label = {
                    Text(
                        name,
                        style = Typography.caption,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isSelected) Color.Black else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    }
}

data class NavigationItem(
    val title: String,
    val icon: Int,
    val screen: KClass<out Screen>,
    val event: BottomNavigationBarEvent
)
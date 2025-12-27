package com.arsir.dev.arsir.feature.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arsir.dev.arsir.feature.login.host.LoginHost
import com.arsir.dev.arsir.navigation.HomeNavigation
import com.arsir.dev.arsir.uikit.theme.StoreTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity: ComponentActivity() {

    @Inject
    lateinit var homeNavigation: HomeNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreTheme {
                LoginHost()
            }
        }
    }

    internal fun navigateToHome() {
        val intent = homeNavigation.goToHome(context = this)
        startActivity(intent)
    }
}
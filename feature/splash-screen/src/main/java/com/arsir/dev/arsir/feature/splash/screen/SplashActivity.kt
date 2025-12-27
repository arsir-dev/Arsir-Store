package com.arsir.dev.arsir.feature.splash.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arsir.dev.arsir.feature.splash.screen.host.SplashHost
import com.arsir.dev.arsir.navigation.HomeNavigation
import com.arsir.dev.arsir.navigation.LoginNavigation
import com.arsir.dev.arsir.navigation.OnboardingNavigation
import com.arsir.dev.arsir.uikit.theme.StoreTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity: ComponentActivity() {

    @Inject
    lateinit var loginNavigation: LoginNavigation

    @Inject
    lateinit var onboardingNavigation: OnboardingNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StoreTheme {
                SplashHost()
            }
        }
    }

    internal fun navigateToLogin() {
        val intent = loginNavigation.gotToLogin(context = this)
        startActivity(intent)
        finish()
    }

    internal fun navigateToOnboarding() {
        val intent = onboardingNavigation.goToOnboarding(context = this)
        startActivity(intent)
        finish()
    }
}
package com.arsir.dev.arsir.feature.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arsir.dev.arsir.feature.onboarding.host.OnboardingHost
import com.arsir.dev.arsir.navigation.LoginNavigation
import com.arsir.dev.arsir.uikit.theme.StoreTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : ComponentActivity() {

    @Inject
    lateinit var loginNavigation: LoginNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreTheme {
                OnboardingHost()
            }
        }
    }

    internal fun navigateToLogin() {
        val intent = loginNavigation.gotToLogin(context = this)
        startActivity(intent)
        finish()
    }
}
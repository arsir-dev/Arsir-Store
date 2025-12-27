package com.arsir.dev.arsir.feature.splash.screen.navigation

import android.content.Context
import android.content.Intent
import com.arsir.dev.arsir.feature.splash.screen.SplashActivity
import com.arsir.dev.arsir.navigation.SplashNavigation
import javax.inject.Inject

class SplashNavigationImpl @Inject constructor(): SplashNavigation {
    override fun goToSplash(context: Context): Intent {
        return Intent(context, SplashActivity::class.java)
    }
}
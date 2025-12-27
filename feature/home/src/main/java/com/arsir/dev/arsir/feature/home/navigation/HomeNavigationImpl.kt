package com.arsir.dev.arsir.feature.home.navigation

import android.content.Context
import android.content.Intent
import com.arsir.dev.arsir.feature.home.HomeActivity
import com.arsir.dev.arsir.navigation.HomeNavigation
import javax.inject.Inject

internal class HomeNavigationImpl @Inject constructor(): HomeNavigation {
    override fun goToHome(context: Context): Intent {
        return Intent(context, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }
}
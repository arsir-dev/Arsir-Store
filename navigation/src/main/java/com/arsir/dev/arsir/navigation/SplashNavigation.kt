package com.arsir.dev.arsir.navigation

import android.content.Context
import android.content.Intent

fun interface SplashNavigation {
    fun goToSplash(context: Context): Intent
}
package com.arsir.dev.arsir.navigation

import android.content.Context
import android.content.Intent

fun interface HomeNavigation {
    fun goToHome(context: Context): Intent
}
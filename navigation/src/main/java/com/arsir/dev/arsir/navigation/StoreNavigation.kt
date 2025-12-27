package com.arsir.dev.arsir.navigation

import android.content.Context
import android.content.Intent

fun interface StoreNavigation {
    fun goToHome(context: Context): Intent
}
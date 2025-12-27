package com.arsir.dev.arsir.navigation

import android.content.Context
import android.content.Intent

fun interface LoginNavigation {
    fun gotToLogin(context: Context): Intent
}
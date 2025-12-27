package com.arsir.dev.arsir.navigation

import android.content.Context
import android.content.Intent

fun interface OnboardingNavigation {
    fun goToOnboarding(context: Context): Intent
}
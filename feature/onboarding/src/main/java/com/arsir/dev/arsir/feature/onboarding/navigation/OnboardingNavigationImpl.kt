package com.arsir.dev.arsir.feature.onboarding.navigation

import android.content.Context
import android.content.Intent
import com.arsir.dev.arsir.feature.onboarding.OnboardingActivity
import com.arsir.dev.arsir.navigation.OnboardingNavigation
import javax.inject.Inject

class OnboardingNavigationImpl @Inject constructor(): OnboardingNavigation {
    override fun goToOnboarding(context: Context): Intent {
        return Intent(context, OnboardingActivity::class.java)
    }
}
package com.arsir.dev.arsir.feature.login.navigation

import android.content.Context
import android.content.Intent
import com.arsir.dev.arsir.feature.login.LoginActivity
import com.arsir.dev.arsir.navigation.LoginNavigation
import javax.inject.Inject

class LoginNavigationImpl @Inject constructor(): LoginNavigation {
    override fun gotToLogin(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }
}
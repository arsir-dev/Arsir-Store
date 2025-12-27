package com.arsir.dev.arsir.feature.splash.screen.screen

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arsir.dev.arsir.feature.splash.screen.SplashActivity
import com.arsir.dev.arsir.observer.observeState
import com.arsir.dev.arsir.uikit.screen.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
data object SplashNavigation : Screen

internal fun NavGraphBuilder.splashScreen() {
    composable<SplashNavigation> {
        val lifecycleOwner = LocalLifecycleOwner.current
        val activity = LocalContext.current as SplashActivity
        val viewModel: SplashViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.onEvent(SplashEvent.OnLoad)
        }

        collectUiEffect(
            activity = activity,
            lifecycleOwner = lifecycleOwner,
            uiEffect = viewModel.effect,
        )

        SplashScreen(isLoading = state.isLoading)
    }
}

private fun collectUiEffect(
    activity: SplashActivity,
    lifecycleOwner: LifecycleOwner,
    uiEffect: Flow<SplashEffect>,
) {
    lifecycleOwner.observeState(
        source = uiEffect,
        context = Dispatchers.Main.immediate
    ) { effect ->
        when(effect) {
            SplashEffect.Login -> activity.navigateToLogin()
            SplashEffect.Onboarding -> activity.navigateToOnboarding()
        }
    }
}

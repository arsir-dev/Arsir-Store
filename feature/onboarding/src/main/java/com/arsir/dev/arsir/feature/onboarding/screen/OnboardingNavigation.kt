package com.arsir.dev.arsir.feature.onboarding.screen

import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arsir.dev.arsir.feature.onboarding.OnboardingActivity
import com.arsir.dev.arsir.observer.observeState
import com.arsir.dev.arsir.uikit.screen.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
internal data object OnboardingDestination : Screen

internal fun NavGraphBuilder.onboardingScreen() {
    composable<OnboardingDestination> {
        val activity = LocalContext.current as OnboardingActivity
        val lifecycleOwner = LocalLifecycleOwner.current
        val viewModel: OnboardingViewModel = hiltViewModel()

        collectUiEffect(
            activity = activity,
            lifecycleOwner = lifecycleOwner,
            uiEffect = viewModel.effect
        )

        OnboardingScreen(onAction = viewModel::onEvent)
    }
}

fun collectUiEffect(
    activity: OnboardingActivity,
    lifecycleOwner: LifecycleOwner,
    uiEffect: Flow<OnboardingEffect>
) {
    lifecycleOwner.observeState(
        source = uiEffect,
        context = Dispatchers.Main.immediate
    ) { effect ->
        when(effect) {
            is OnboardingEffect.OnFinish -> activity.navigateToLogin()
            else -> Unit
        }
    }
}

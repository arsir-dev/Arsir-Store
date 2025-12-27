package com.arsir.dev.arsir.feature.login.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arsir.dev.arsir.feature.login.LoginActivity
import com.arsir.dev.arsir.observer.observeState
import com.arsir.dev.arsir.uikit.modal.ModalGeneral
import com.arsir.dev.arsir.uikit.modal.ModalType
import com.arsir.dev.arsir.uikit.screen.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
internal data object LoginNavigation : Screen

internal fun NavGraphBuilder.loginScreen() {
    composable<LoginNavigation> {
        val lifecycleOwner = LocalLifecycleOwner.current
        val activity = LocalContext.current as LoginActivity
        val viewModel: LoginViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        var modalState by remember { mutableStateOf<LoginModalState>(LoginModalState.Hidden) }

        collectEffect(
            activity = activity,
            lifecycleOwner = lifecycleOwner,
            uiEffect = viewModel.effect,
            onModalTypeChange = { type, message ->
                modalState = LoginModalState.Shown(
                    type = type,
                    message = message,
                )
            }
        )

        LoginScreen(
            state = state,
            onAction = viewModel::onEvent
        )

        RenderModal(
            message = (modalState as? LoginModalState.Shown)?.message,
            modalState = (modalState as? LoginModalState.Shown)?.type,
            onModalStateChange = { type -> modalState = type }
        )
    }
}

private fun collectEffect(
    activity: LoginActivity,
    lifecycleOwner: LifecycleOwner,
    uiEffect: Flow<LoginEffect>,
    onModalTypeChange: (type: ModalType, message: String) -> Unit,
) {
    lifecycleOwner.observeState(
        source = uiEffect,
        context = Dispatchers.Main.immediate,
    ) { effect ->
        when(effect) {
            is LoginEffect.OnHome -> activity.navigateToHome()
            is LoginEffect.OnError -> {
                onModalTypeChange(
                    ModalType.GeneralError,
                    effect.message
                )
            }
        }
    }
}

@Composable
private fun RenderModal(
    message: String?,
    modalState: ModalType?,
    onModalStateChange: (LoginModalState) -> Unit,
) {
    when(modalState) {
        ModalType.GeneralError -> ModalGeneral(message = message.orEmpty()) {
            onModalStateChange(LoginModalState.Hidden)
        }
        else -> Unit
    }
}
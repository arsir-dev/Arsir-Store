package com.arsir.dev.arsir.feature.detail.screen

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
import com.arsir.dev.arsir.feature.detail.DetailActivity
import com.arsir.dev.arsir.observer.observeState
import com.arsir.dev.arsir.uikit.modal.ModalGeneral
import com.arsir.dev.arsir.uikit.modal.ModalLoading
import com.arsir.dev.arsir.uikit.modal.ModalSuccess
import com.arsir.dev.arsir.uikit.modal.ModalType
import com.arsir.dev.arsir.uikit.screen.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
internal data class DetailDestination(
    val productId: Int
) : Screen

internal fun NavGraphBuilder.detailScreen() {
    composable<DetailDestination> {
        val activity = LocalContext.current as DetailActivity
        val lifecycleOwner = LocalLifecycleOwner.current
        val viewModel: DetailViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        var modalState by remember { mutableStateOf<DetailModalState>(DetailModalState.Hidden) }

        collectUiEffect(
            activity = activity,
            lifecycleOwner = lifecycleOwner,
            uiEffect = viewModel.effect,
            onModalTypeChange = { type, message ->
                modalState = DetailModalState.Shown(
                    type = type,
                    message = message,
                )
            },
            onModalLoadingTypeChange = { type, isLoading ->
                if (isLoading) {
                    modalState = DetailModalState.Shown(type = type)
                } else {
                    modalState = DetailModalState.Hidden
                }
            }
        )

        DetailScreen(
            state = state,
            onAction = viewModel::onEvent
        )

        RenderModal(
            message = (modalState as? DetailModalState.Shown)?.message,
            modalState = (modalState as? DetailModalState.Shown)?.type,
            onModalStateChange = { type -> modalState = type },
            onModalSuccess = {
                activity.apply {
                    setResult(android.app.Activity.RESULT_OK)
                    finish()
                }
            }
        )
    }
}

private fun collectUiEffect(
    activity: DetailActivity,
    lifecycleOwner: LifecycleOwner,
    uiEffect: Flow<DetailEffect>,
    onModalTypeChange: (ModalType, String) -> Unit,
    onModalLoadingTypeChange: (ModalType, Boolean) -> Unit
) {
    lifecycleOwner.observeState(
        source = uiEffect,
        context = Dispatchers.Main.immediate,
    ) { effect ->
        when(effect) {
            is DetailEffect.OnBack -> activity.finish()
            is DetailEffect.OnLoading -> onModalLoadingTypeChange(ModalType.Loading, effect.isLoading)
            is DetailEffect.OnSuccess -> onModalTypeChange(ModalType.Success, "")
            is DetailEffect.OnGeneralError -> onModalTypeChange(
                ModalType.GeneralError,
                effect.message
            )
        }
    }
}

@Composable
private fun RenderModal(
    message: String?,
    modalState: ModalType?,
    onModalStateChange: (DetailModalState) -> Unit,
    onModalSuccess: () -> Unit
) {
    when(modalState) {
        ModalType.Loading -> ModalLoading()
        ModalType.Success -> ModalSuccess("Data berhasil di input") {
            onModalStateChange(DetailModalState.Hidden)
            onModalSuccess()
        }
        ModalType.GeneralError -> ModalGeneral(message = message.orEmpty()) {
            onModalStateChange(DetailModalState.Hidden)
        }
        else -> Unit
    }
}
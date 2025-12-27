package com.arsir.dev.arsir.feature.home.screen.search

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.arsir.dev.arsir.feature.home.HomeActivity
import com.arsir.dev.arsir.feature.home.screen.cart.CartDestination
import com.arsir.dev.arsir.observer.observeState
import com.arsir.dev.arsir.uikit.modal.ModalGeneral
import com.arsir.dev.arsir.uikit.modal.ModalType
import com.arsir.dev.arsir.uikit.screen.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
internal data object SearchDestination : Screen

internal fun NavGraphBuilder.searchScreen(navController: NavHostController) {
    composable<SearchDestination> {
        val activity = LocalContext.current as HomeActivity
        val lifecycleOwner = LocalLifecycleOwner.current
        val viewModel: SearchViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        var modalState by remember { mutableStateOf<SearchModalState>(SearchModalState.Hidden) }
        val resultLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                navController.navigate(CartDestination)
            }
        }

        BackHandler {
            navController.navigateUp()
        }

        LaunchedEffect(Unit) {
            viewModel.onEvent(SearchEvent.OnFetching)
        }

        collectUiEffect(
            lifecycleOwner = lifecycleOwner,
            uiEffect = viewModel.effect,
            onDetail = { productId ->
                val intent = activity.navigateToDetail(productId = productId)
                resultLauncher.launch(intent)
            },
            onModalTypeChange = { type, message ->
                modalState = SearchModalState.Shown(
                    type = type,
                    message = message,
                )
            }
        )

        SearchScreen(
            state = state,
            onAction = viewModel::onEvent
        )

        RenderModal(
            message = (modalState as? SearchModalState.Shown)?.message,
            modalState = (modalState as? SearchModalState.Shown)?.type,
            onModalStateChange = { type -> modalState = type }
        )
    }
}

private fun collectUiEffect(
    lifecycleOwner: LifecycleOwner,
    uiEffect: Flow<SearchEffect>,
    onDetail: (Int) -> Unit,
    onModalTypeChange: (ModalType, String) -> Unit,
) {
    lifecycleOwner.observeState(
        source = uiEffect,
        context = Dispatchers.Main.immediate,
    ) { effect ->
        when(effect) {
            is SearchEffect.OnDetail -> onDetail(effect.productId)
            is SearchEffect.OnGeneralError -> onModalTypeChange(
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
    onModalStateChange: (SearchModalState) -> Unit,
) {
    when(modalState) {
        ModalType.GeneralError -> ModalGeneral(message = message.orEmpty()) {
            onModalStateChange(SearchModalState.Hidden)
        }
        else -> Unit
    }
}
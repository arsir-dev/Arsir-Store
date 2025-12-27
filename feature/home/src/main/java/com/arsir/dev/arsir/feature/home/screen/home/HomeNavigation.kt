package com.arsir.dev.arsir.feature.home.screen.home

import android.app.Activity
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
import com.arsir.dev.arsir.feature.home.screen.profile.ProfileDestination
import com.arsir.dev.arsir.feature.home.screen.search.SearchDestination
import com.arsir.dev.arsir.observer.observeState
import com.arsir.dev.arsir.uikit.modal.ModalGeneral
import com.arsir.dev.arsir.uikit.modal.ModalType
import com.arsir.dev.arsir.uikit.screen.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
internal data object HomeDestination : Screen

internal fun NavGraphBuilder.homeScreen(navController: NavHostController) {
    composable<HomeDestination> {
        val activity = LocalContext.current as HomeActivity
        val lifecycleOwner = LocalLifecycleOwner.current
        val viewModel: HomeViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        var modalState by remember { mutableStateOf<HomeModalState>(HomeModalState.Hidden) }
        val resultLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                navController.navigate(CartDestination)
            }
        }

        LaunchedEffect(Unit) {
            viewModel.onEvent(HomeEvent.OnFetching)
        }

        collectUiEffect(
            navController = navController,
            lifecycleOwner = lifecycleOwner,
            uiEffect = viewModel.effect,
            onDetail = { productId ->
                val intent = activity.navigateToDetail(productId = productId)
                resultLauncher.launch(intent)
            },
            onModalTypeChange = { type, message ->
                modalState = HomeModalState.Shown(
                    type = type,
                    message = message,
                )
            }
        )

        HomeScreen(
            state = state,
            onAction = viewModel::onEvent
        )

        RenderModal(
            message = (modalState as? HomeModalState.Shown)?.message,
            modalState = (modalState as? HomeModalState.Shown)?.type,
            onModalStateChange = { type -> modalState = type }
        )
    }
}

private fun collectUiEffect(
    navController: NavHostController,
    lifecycleOwner: LifecycleOwner,
    uiEffect: Flow<HomeEffect>,
    onDetail: (Int) -> Unit,
    onModalTypeChange: (ModalType, String) -> Unit,
) {
    lifecycleOwner.observeState(
        source = uiEffect,
        context = Dispatchers.Main.immediate,
    ) { effect ->
        when(effect) {
            is HomeEffect.OnSearch, HomeEffect.OnSeeAllCategory -> {
                navController.navigate(SearchDestination)
            }
            is HomeEffect.OnProfile -> navController.navigate(ProfileDestination)
            is HomeEffect.OnGeneralError -> onModalTypeChange(
                ModalType.GeneralError,
                effect.message
            )
            is HomeEffect.OnDetail -> onDetail(effect.productId)
        }
    }
}

@Composable
private fun RenderModal(
    message: String?,
    modalState: ModalType?,
    onModalStateChange: (HomeModalState) -> Unit,
) {
    when(modalState) {
        ModalType.GeneralError -> ModalGeneral(message = message.orEmpty()) {
            onModalStateChange(HomeModalState.Hidden)
        }
        else -> Unit
    }
}
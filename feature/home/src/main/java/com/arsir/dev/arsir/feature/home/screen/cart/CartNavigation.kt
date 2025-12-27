package com.arsir.dev.arsir.feature.home.screen.cart

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arsir.dev.arsir.feature.home.screen.checkout.CheckoutDestination
import com.arsir.dev.arsir.feature.home.screen.search.SearchDestination
import com.arsir.dev.arsir.observer.observeState
import com.arsir.dev.arsir.uikit.modal.ModalGeneral
import com.arsir.dev.arsir.uikit.modal.ModalType
import com.arsir.dev.arsir.uikit.screen.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Serializable
internal data object CartDestination : Screen

internal fun NavGraphBuilder.cartScreen(navController: NavHostController) {
    composable<CartDestination> {
        val lifecycleOwner = LocalLifecycleOwner.current
        val viewModel: CartViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        var modalState by remember { mutableStateOf<CartModalState>(CartModalState.Hidden) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val isCheckoutSheetVisible = navBackStackEntry?.destination?.route ==
                CheckoutDestination::class.qualifiedName

        BackHandler {
            if (isCheckoutSheetVisible) {
                navController.navigateUp()
            } else {
                viewModel.onEvent(CartEvent.OnBack)
            }
        }

        collectUiEffect(
            navController = navController,
            lifecycleOwner = lifecycleOwner,
            uiEffect = viewModel.effect,
            onModalTypeChange = { type, message ->
                modalState = CartModalState.Shown(
                    type = type,
                    message = message,
                )
            }
        )

        CartScreen(
            state = state,
            onAction = viewModel::onEvent,
            isCheckoutSheetVisible = isCheckoutSheetVisible,
        )

        RenderModal(
            message = (modalState as? CartModalState.Shown)?.message,
            modalState = (modalState as? CartModalState.Shown)?.type,
            onModalStateChange = { type -> modalState = type }
        )
    }
}

private fun collectUiEffect(
    navController: NavHostController,
    lifecycleOwner: LifecycleOwner,
    uiEffect: Flow<CartEffect>,
    onModalTypeChange: (ModalType, String) -> Unit,
) {
    lifecycleOwner.observeState(
        source = uiEffect,
        context = Dispatchers.Main.immediate,
    ) { effect ->
        when(effect) {
            is CartEffect.OnBack -> navController.navigateUp()
            is CartEffect.OnCheckout -> navController.navigate(
                CheckoutDestination(subTotal = effect.subTotal)
            )
            is CartEffect.OnSearch -> navController.navigate(SearchDestination)
            is CartEffect.OnGeneralError -> onModalTypeChange(
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
    onModalStateChange: (CartModalState) -> Unit,
) {
    when(modalState) {
        ModalType.GeneralError -> ModalGeneral(message = message.orEmpty()) {
            onModalStateChange(CartModalState.Hidden)
        }
        else -> Unit
    }
}
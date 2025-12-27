package com.arsir.dev.arsir.feature.home.screen.cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.feature.home.component.Empty
import com.arsir.dev.arsir.feature.home.screen.cart.section.CartHeader
import com.arsir.dev.arsir.feature.home.screen.cart.section.CartLoading
import com.arsir.dev.arsir.feature.home.screen.cart.section.CartProduct
import com.arsir.dev.arsir.uikit.event.OnAction
import com.arsir.dev.arsir.uikit.theme.Typography

@Composable
internal fun CartScreen(
    state: CartState,
    onAction: OnAction,
    modifier: Modifier = Modifier,
    isCheckoutSheetVisible: Boolean
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp, end = 16.dp, start = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CartHeader(
                query = state.query,
                onAction = onAction
            )

            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    state.isLoading -> {
                        CartLoading()
                    }

                    state.carts.isEmpty() -> {
                        Empty(isSearching = state.query.isNotBlank())
                    }

                    else -> {
                        LazyColumn(
                            modifier = modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                top = 16.dp,
                                bottom = 120.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            items(
                                items = state.carts,
                                key = { it.id }
                            ) { cart ->
                                CartProduct(
                                    cart = cart,
                                    onIncrease = {
                                        onAction(CartEvent.OnIncrease(productId = cart.id))
                                    },
                                    onDecrease = {
                                        onAction(CartEvent.OnDecrease(productId = cart.id))
                                    },
                                    onRemove = {
                                        onAction(CartEvent.OnRemove(productId = cart.id))
                                    },
                                    onFavorite = {
                                        onAction(CartEvent.OnFavorite(cart = cart))
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
        if (state.carts.isNotEmpty() && !isCheckoutSheetVisible) {
            CheckoutFloatingButton(
                totalPrice = state.price,
                onClick = { onAction(CartEvent.OnCheckout(subTotal = state.price.toLong())) },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun CheckoutFloatingButton(
    totalPrice: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        color = Color(0xFF9CD84E),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() }
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$${"%.2f".format(totalPrice)}",
                color = Color.White,
                style = Typography.caption,
                fontWeight = FontWeight.Bold
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Checkout",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview
@Composable
private fun PreviewCartScreen() {
    CartScreen(
        state = CartState(),
        onAction = {},
        isCheckoutSheetVisible = false
    )
}
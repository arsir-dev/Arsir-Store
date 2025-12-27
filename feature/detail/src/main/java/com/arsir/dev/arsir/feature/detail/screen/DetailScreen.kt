package com.arsir.dev.arsir.feature.detail.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.feature.detail.screen.section.AddCart
import com.arsir.dev.arsir.feature.detail.screen.section.ExpandableDescription
import com.arsir.dev.arsir.feature.detail.screen.section.ImageWithRating
import com.arsir.dev.arsir.uikit.R
import com.arsir.dev.arsir.uikit.circle.CircleIconButton
import com.arsir.dev.arsir.uikit.event.OnAction
import com.arsir.dev.arsir.uikit.theme.Typography

@Composable
internal fun DetailScreen(
    state: DetailState,
    onAction: OnAction,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            Box {
                DetailHeader(
                    onAction = onAction,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .zIndex(1f)
                )
                ImageWithRating(state = state)

            }
        },
        bottomBar = {
            AddCart(
                price = state.totalPrice,
                onAction = {
                    onAction(DetailEvent.OnUpsert(product = state.product))
                },
            )
        },
    ) { paddingValue ->
        Column(
            modifier = modifier
                .padding(paddingValues = paddingValue)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = state.product.title,
                        style = Typography.subtitle1,
                        color = Color.Black.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Bold,
                        lineHeight = 18.sp,
                        letterSpacing = 0.1.sp
                    )
                    Text(
                        text = state.product.category,
                        style = Typography.overline,
                        color = Color.Gray.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Normal,
                        lineHeight = 16.sp,
                        letterSpacing = 0.01.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
            QuantityControl(
                quantity = state.quantity,
                onIncrease = { onAction(DetailEvent.OnIncrease) },
                onDecrease = { onAction(DetailEvent.OnDecrease) }
            )
            ExpandableDescription(text = state.product.description)
        }
    }
}

@Composable
private fun QuantityControl(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .background(
                color = Color(0xFFD77A3D).copy(alpha = 0.4f),
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(4.dp)
    ) {
        CircleIconButton(
            size = 25.dp,
            borderStroke = BorderStroke(
                width = 1.dp,
                color = Color(0xFF6B6B6B)
            ),
            contentColor = Color(0xFF6B6B6B),
            backgroundCircle = Color.White.copy(alpha = 0.6f),
            icon = ImageVector.vectorResource(R.drawable.ic_minus),
            contentDescription = null
        ) { onDecrease() }

        Text(
            text = quantity.toString().padStart(2, '0'),
            style = Typography.caption,
            color = Color(0xFF1C1C1E),
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.01.sp
        )

        CircleIconButton(
            size = 25.dp,
            contentColor = Color.White,
            backgroundCircle = Color(0xFF1C1C1E).copy(alpha = 0.7f),
            icon = ImageVector.vectorResource(R.drawable.ic_add),
            contentDescription = null
        ) { onIncrease() }
    }
}

@Preview
@Composable
private fun PreviewDetailScreen() {
    DetailScreen(
        state = DetailState(
            product = Product(
                title = "Title",
                category = "Men",
                description = "" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n" +
                        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident."
            )
        ),
        onAction = {}
    )
}
package com.arsir.dev.arsir.feature.home.screen.checkout

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arsir.dev.arsir.uikit.R
import com.arsir.dev.arsir.uikit.button.Button
import com.arsir.dev.arsir.uikit.button.ButtonAppearance
import com.arsir.dev.arsir.uikit.ext.round
import com.arsir.dev.arsir.uikit.theme.Typography
import kotlin.random.Random

@Composable
internal fun CheckoutScreen(
    subTotal: Double,
    fee: Double = Random.nextDouble(from = 0.0, until = 50.0).round(),
    onDismiss: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(CircleShape)
                .background(color = Color.Gray.copy(alpha = 0.2f))
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_discount),
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Apply a promo code",
                style = Typography.caption,
                lineHeight = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(24.dp))
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Color(0xFFB8E06E))
                    .padding(8.dp)
            ) {
                Text(
                    text = "Apply",
                    style = Typography.caption,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Subtotal:",
                style = Typography.caption,
                lineHeight = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "$$subTotal",
                style = Typography.caption,
                lineHeight = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Delivery Fee:",
                style = Typography.caption,
                lineHeight = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "$$fee",
                style = Typography.caption,
                lineHeight = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Total Amount:",
                style = Typography.caption,
                lineHeight = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "$${(subTotal + fee).round()}",
                style = Typography.caption,
                lineHeight = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Button(
            onClick = {
                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                onDismiss()
            },
            modifier = Modifier.fillMaxWidth(),
            appearance = ButtonAppearance.Primary,
            text = "Checkout"
        )
    }
}

@Preview
@Composable
private fun PreviewCheckoutBottomSheet() {
    CheckoutScreen(
        subTotal = 25.50
    ) { }
}
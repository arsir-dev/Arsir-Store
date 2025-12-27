package com.arsir.dev.arsir.uikit.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button as Buttons
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arsir.dev.arsir.uikit.R
import com.arsir.dev.arsir.uikit.theme.Typography

@Composable
fun Button(
    text: String,
    appearance: ButtonAppearance,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    isWithIcon: Boolean = false,
) {
    when(appearance) {
        ButtonAppearance.Primary -> {
            PrimaryButton(
                text = text,
                enabled = enabled,
                modifier = modifier,
                onClick = onClick,
                isLoading = isLoading,
                isWithIcon = isWithIcon,
            )
        }
        ButtonAppearance.Outline -> {
            OutlineButton(
                text = text,
                enabled = enabled,
                modifier = modifier,
                onClick = onClick,
                isLoading = isLoading,
                isWithIcon = isWithIcon,
            )
        }
    }
}

@Composable
private fun PrimaryButton(
    text: String,
    enabled: Boolean,
    isLoading: Boolean,
    isWithIcon: Boolean,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFD7F08E),
            Color(0xFFB8E06E)
        )
    )

    Buttons(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Transparent,
            disabledContentColor = Color(0xFFE0E0E0)
        ),
        contentPadding = PaddingValues(),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = gradient,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            ButtonContent(
                text = text,
                color = Color.White,
                isLoading = isLoading,
                isWithIcon = isWithIcon,
            )
        }
    }
}

@Composable
private fun OutlineButton(
    text: String,
    enabled: Boolean,
    isLoading: Boolean,
    isWithIcon: Boolean,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            1.dp,
            Color(0xFFE0E0E0)
        ),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF6E6E6E))
    ) {
        ButtonContent(
            text = text,
            color = MaterialTheme.colors.primary,
            isLoading = isLoading,
            isWithIcon = isWithIcon
        )
    }
}


@Composable
fun ButtonContent(
    text: String,
    color: Color,
    isLoading: Boolean,
    isWithIcon: Boolean,
) {
    when {
        isLoading -> {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = color,
            )
        }
        isWithIcon -> {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_cart),
                    contentDescription = null,
                    Modifier.size(24.dp),
                    tint = Color.White
                )
                Text(
                    text = text,
                    color = Color.White,
                    style = Typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 18.sp,
                )
            }
        }
        else -> {
            Text(
                text = text,
                color = Color(0xFF3E5F0B),
                style = Typography.body1,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 18.sp,
            )
        }
    }
}
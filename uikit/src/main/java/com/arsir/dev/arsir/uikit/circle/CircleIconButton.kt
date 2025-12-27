package com.arsir.dev.arsir.uikit.circle

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircleIconButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    borderStroke: BorderStroke = BorderStroke(width = 0.dp, color = Color.Unspecified),
    contentColor: Color = Color.Black,
    backgroundCircle: Color = Color(0xFFF6F6F6),
    size: Dp = 40.dp,
    contentDescription: String?,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(
                border = borderStroke,
                shape = CircleShape
            )
            .background(backgroundCircle)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(size / 2),
            tint = contentColor,
        )
    }
}

@Preview
@Composable
private fun PreviewCircleIconButton() {
    CircleIconButton(
        icon = Icons.Outlined.AccountCircle,
        contentDescription = null
    ) { }
}
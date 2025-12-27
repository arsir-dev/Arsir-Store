package com.arsir.dev.arsir.feature.detail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arsir.dev.arsir.uikit.circle.CircleIconButton
import com.arsir.dev.arsir.uikit.event.OnAction
import com.arsir.dev.arsir.uikit.theme.Typography

@Composable
internal fun DetailHeader(
    onAction: OnAction,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space = 110.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleIconButton(
            icon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
            contentDescription = null,
            onClick = { onAction(DetailEvent.OnBack) }
        )
        Text(
            text = "Detail Product",
            style = Typography.overline,
            fontWeight = FontWeight.Bold,
            lineHeight = 14.sp,
            letterSpacing = 0.1.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun PreviewDetailHeader() {
    DetailHeader(onAction = {})
}
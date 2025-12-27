package com.arsir.dev.arsir.feature.detail.screen.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.uikit.theme.Typography

@Composable
internal fun ExpandableDescription(
    text: String,
    modifier: Modifier = Modifier,
    collapsedMaxLine: Int = 3,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var isOverflowing by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = text,
            style = Typography.body1,
            color = Color(0xFF6B7280),
            maxLines = if (expanded) Int.MAX_VALUE else collapsedMaxLine,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                if (!expanded) {
                    isOverflowing = textLayoutResult.hasVisualOverflow
                }
            }
        )

        if (isOverflowing || expanded) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = if (expanded) "Read less" else "Read more",
                style = Typography.body1,
                color = Color.Black,
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        expanded = !expanded
                    }
            )
        }
    }
}

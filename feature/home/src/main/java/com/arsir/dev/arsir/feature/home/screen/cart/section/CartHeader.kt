package com.arsir.dev.arsir.feature.home.screen.cart.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.feature.home.screen.cart.CartEvent
import com.arsir.dev.arsir.feature.home.screen.home.HomeEvent
import com.arsir.dev.arsir.uikit.circle.CircleIconButton
import com.arsir.dev.arsir.uikit.event.OnAction
import com.arsir.dev.arsir.uikit.textfield.AppTextField
import com.arsir.dev.arsir.uikit.textfield.TextFieldAppearance

@Composable
internal fun CartHeader(
    query: String,
    onAction: OnAction,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onAction(HomeEvent.OnSearch) },
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleIconButton(
            icon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
            contentDescription = null,
            onClick = {
                onAction(CartEvent.OnBack)
            }
        )

        AppTextField(
            value = query,
            onValueChange = { query ->
                onAction(CartEvent.OnValueChange(query = query))
            },
            appearance = TextFieldAppearance.Search,
            placeholder = "Search...",
            leadingIcon = Icons.Outlined.Search,
            modifier = Modifier.weight(1f),
        )
        CircleIconButton(
            icon = Icons.Outlined.Settings,
            contentDescription = null
        ) { }
    }
}

@Preview
@Composable
private fun PreviewCartHeader() {
    CartHeader(
        query = "",
        onAction = {}
    )
}
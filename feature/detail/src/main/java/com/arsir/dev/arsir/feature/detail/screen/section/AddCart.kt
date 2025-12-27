package com.arsir.dev.arsir.feature.detail.screen.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.uikit.button.Button
import com.arsir.dev.arsir.uikit.button.ButtonAppearance
import com.arsir.dev.arsir.uikit.ext.round

@Composable
internal fun AddCart(
    price: Double ,
    onAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Button(
            text = "$${price.round()}",
            appearance = ButtonAppearance.Outline,
            onClick = {},
            modifier = Modifier.weight(1f),
            enabled = false
        )
        Button(
            text = "Add to Cart",
            isWithIcon = true,
            appearance = ButtonAppearance.Primary,
            modifier = Modifier.weight(2f),
            onClick = { onAction() },
        )
    }
}

@Preview
@Composable
private fun PreviewAddCart() {
    AddCart(price = 0.0, onAction = {})
}
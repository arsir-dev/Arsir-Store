package com.arsir.dev.arsir.feature.detail.screen.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arsir.dev.arsir.core.common.ext.orEmpty
import com.arsir.dev.arsir.feature.detail.screen.DetailState
import com.arsir.dev.arsir.uikit.theme.Typography

@Composable
internal fun ImageWithRating(
    state: DetailState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(color = Color.Black.copy(alpha = 0.4f)),
        contentAlignment = Alignment.BottomStart,
    ) {
        AsyncImage(
            model = state.product.image,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 56.dp)
                .height(260.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
        Row(
            modifier = Modifier.padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 4.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = "${state.product.rating.rate}",
                style = Typography.caption,
                color = Color.Black
            )
            Text(
                text = "(${state.product.rating.count.orEmpty()})",
                style = Typography.caption,
                color = Color.Black.copy(
                    alpha = 0.8f
                ),
            )
        }
    }
}
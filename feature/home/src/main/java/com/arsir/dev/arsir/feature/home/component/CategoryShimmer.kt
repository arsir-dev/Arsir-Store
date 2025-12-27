package com.arsir.dev.arsir.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.uikit.shimmer.shimmerEffect

@Composable
internal fun CategoryShimmer() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(5) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .shimmerEffect(),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCategoryShimmer() {
    CategoryShimmer()
}
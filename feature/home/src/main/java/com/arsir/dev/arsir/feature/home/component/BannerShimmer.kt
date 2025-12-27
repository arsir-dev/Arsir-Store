package com.arsir.dev.arsir.feature.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.uikit.shimmer.shimmerEffect

@Composable
internal fun BannerShimmer() {
    Box(
        modifier = Modifier
            .height(220.dp)
            .fillMaxWidth()
            .shimmerEffect()
    )
}

@Preview
@Composable
private fun PreviewBannerShimmer() {
    BannerShimmer()
}
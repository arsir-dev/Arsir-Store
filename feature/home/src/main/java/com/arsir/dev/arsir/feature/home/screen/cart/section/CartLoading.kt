package com.arsir.dev.arsir.feature.home.screen.cart.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.feature.home.component.ProductShimmer

@Composable
internal fun CartLoading(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(
            count = 2,
            key = { "shimmer $it" }
        ) {
            ProductShimmer()
        }
    }
}

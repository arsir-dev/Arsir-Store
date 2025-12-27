package com.arsir.dev.arsir.feature.home.screen.search.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.feature.home.component.ProductImageSection
import com.arsir.dev.arsir.feature.home.component.ProductInfoSection

@Composable
internal fun SearchProduct(
    product: Product,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(200.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        ProductImageSection(
            imageUrl = product.image,
            price = product.price.toString(),
            isFavorite = product.isFavorite,
            onFavoriteClick = onFavoriteClick
        )
        ProductInfoSection(
            title = product.title,
            rate = product.rating.rate,
            count = product.rating.count
        )
    }
}
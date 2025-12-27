package com.arsir.dev.arsir.feature.home.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.feature.home.component.Empty
import com.arsir.dev.arsir.feature.home.component.ProductShimmer
import com.arsir.dev.arsir.feature.home.screen.search.section.SearchHeader
import com.arsir.dev.arsir.feature.home.screen.search.section.SearchProduct
import com.arsir.dev.arsir.uikit.event.OnAction
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun SearchScreen(
    state: SearchState,
    onAction: OnAction,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .fillMaxSize()
    ) {
        SearchHeader(
            query = state.query,
            categories = state.category.toPersistentList(),
            onAction = onAction,
        )
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.isLoading -> {
                    ProductShimmer()
                }
                state.products.isEmpty()-> {
                    Empty(isSearching = state.query.isNotBlank())
                }
                else -> {
                    LazyVerticalGrid(
                        modifier = modifier.fillMaxSize(),
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(
                            items = state.products,
                            key = { it.id }
                        ) { product ->
                            SearchProduct(
                                product = product,
                                onFavoriteClick = { onAction(SearchEvent.OnFavorite(product = product)) }
                            )
                        }
                    }
                }
            }
        }
    }
}
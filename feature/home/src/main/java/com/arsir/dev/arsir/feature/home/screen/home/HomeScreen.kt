package com.arsir.dev.arsir.feature.home.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.feature.home.component.BannerShimmer
import com.arsir.dev.arsir.feature.home.component.CategoryShimmer
import com.arsir.dev.arsir.feature.home.component.GreetingBanner
import com.arsir.dev.arsir.feature.home.component.ProductShimmer
import com.arsir.dev.arsir.feature.home.screen.home.section.HomeCarousel
import com.arsir.dev.arsir.feature.home.screen.home.section.HomeCategory
import com.arsir.dev.arsir.feature.home.screen.home.section.HomeHeader
import com.arsir.dev.arsir.feature.home.screen.home.section.HomeProduct
import com.arsir.dev.arsir.feature.home.screen.home.section.HomeSearch
import com.arsir.dev.arsir.uikit.event.OnAction
import kotlinx.collections.immutable.toPersistentList

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun HomeScreen(
    state: HomeState,
    onAction: OnAction,
    modifier: Modifier = Modifier
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = { onAction(HomeEvent.OnRefresh) }
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                if (state.isLoading) {
                    GreetingBanner()
                } else {
                    HomeHeader(
                        userName = "Arfan",
                        onProfileClick = {
                            onAction(HomeEvent.OnProfile)
                        },
                        onNotificationClick = {},
                        onBagClick = {},
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                HomeSearch(onAction = onAction)
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                if (state.isLoading) {
                    BannerShimmer()
                } else {
                    HomeCarousel(
                        banner = state.banner.toPersistentList(),
                       onAction = onAction
                    )
                }
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                if (state.isLoading) {
                    CategoryShimmer()
                } else {
                    HomeCategory(
                        categories = state.category.toPersistentList(),
                        onCategory = { category ->
                            onAction(HomeEvent.OnCategory(category = category))
                        },
                        onSeeAllClick = {
                            onAction(HomeEvent.OnSeeAllCategory)
                        }
                    )
                }
            }
            if (state.isLoading || state.isShimmerPopular) {
                items(
                    count = 2,
                    key = { "popular shimmer $it" }
                ) {
                    ProductShimmer()
                }
            } else {
                items(
                    items = state.popular,
                    key = { it.id }
                ) { product ->
                    HomeProduct(
                        product = product,
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) { onAction(HomeEvent.OnDetail(productId = product.id)) },
                        onFavoriteClick = { onAction(HomeEvent.OnFavorite(product = product)) }
                    )
                }
            }
        }

        PullRefreshIndicator(
            refreshing = state.isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        state = HomeState(),
        onAction = {}
    )
}
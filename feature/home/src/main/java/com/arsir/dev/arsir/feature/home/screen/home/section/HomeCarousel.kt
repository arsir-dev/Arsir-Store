package com.arsir.dev.arsir.feature.home.screen.home.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.feature.home.screen.home.HomeEvent
import com.arsir.dev.arsir.uikit.event.OnAction
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.delay

@Composable
internal fun HomeCarousel(
    onAction: OnAction,
    banner: PersistentList<Product>,
    modifier: Modifier = Modifier,
) {
    if (banner.isEmpty()) return

    val pagerState = rememberPagerState { banner.size }
    val isDragInteraction = remember { mutableStateOf(false) }
    LaunchedEffect(pagerState.interactionSource) {
        pagerState.interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is DragInteraction.Start -> isDragInteraction.value = true
                is DragInteraction.Stop -> isDragInteraction.value = false
                is DragInteraction.Cancel -> isDragInteraction.value = false
            }
        }
    }
    LaunchedEffect(banner, isDragInteraction.value) {
        while (!isDragInteraction.value) {
            delay(3_000L)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Black.copy(alpha = 0.4f))
    ) {
        HorizontalPager(
            key = { page -> banner[page].id },
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
        ) { page ->
            CarouselItem(
                product = banner.distinct()[page],
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { onAction(HomeEvent.OnDetail(productId = banner[page].id)) }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        PagerIndicator(
            pageCount = banner.size,
            currentPage = pagerState.currentPage
        )
    }
}

@Composable
private fun CarouselItem(
    product: Product,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = product.image,
        contentDescription = null,
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
private fun PagerIndicator(
    pageCount: Int,
    currentPage: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage
            Box(
                modifier = Modifier
                    .padding(all = 4.dp)
                    .height(6.dp)
                    .width(if (isSelected) 18.dp else 6.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected)
                            MaterialTheme.colors.primary
                        else
                            MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
                    )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHomeBanner() {
    HomeCarousel(banner = persistentListOf(), onAction = {})
}
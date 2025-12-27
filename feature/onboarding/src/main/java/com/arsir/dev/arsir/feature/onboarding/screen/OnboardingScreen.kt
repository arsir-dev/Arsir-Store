package com.arsir.dev.arsir.feature.onboarding.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arsir.dev.arsir.feature.onboarding.component.OnboardingPageContent
import com.arsir.dev.arsir.feature.onboarding.component.PagerIndicator
import com.arsir.dev.arsir.uikit.R
import com.arsir.dev.arsir.uikit.button.Button
import com.arsir.dev.arsir.uikit.button.ButtonAppearance
import com.arsir.dev.arsir.uikit.event.OnAction
import com.arsir.dev.arsir.uikit.theme.Typography
import kotlinx.coroutines.launch

@Composable
internal fun OnboardingScreen(
    onAction: OnAction,
    modifier: Modifier = Modifier
) {
    val onboardingPages = listOf(
        OnboardingPage(
            image = R.drawable.ils_first_shopping,
            title = "Discover Products",
            description = "Explore a wide range of products from various categories and find exactly what you are looking for."
        ),
        OnboardingPage(
            image = R.drawable.ils_second_shopping,
            title = "Easy Shopping Experience",
            description = "Add items to your cart, manage quantities, and save your favorites with a smooth and simple experience."
        ),
        OnboardingPage(
            image = R.drawable.ils_third_shopping,
            title = "Fast & Secure Checkout",
            description = "Complete your purchase quickly with a secure checkout process and track your orders effortlessly."
        )
    )

    val pagerState = rememberPagerState { onboardingPages.size }
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.CenterHorizontally,
            ),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_store),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "Ar Store",
                style = Typography.h5,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp,
                letterSpacing = 0.08.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPageContent(page = onboardingPages[page])
        }

        Spacer(modifier = Modifier.height(16.dp))
        PagerIndicator(
            pageCount = onboardingPages.size,
            currentPage = pagerState.currentPage
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            text = if (pagerState.currentPage == onboardingPages.lastIndex)
                "Sign in"
            else
                "Next",
            onClick = {
                if (pagerState.currentPage == onboardingPages.lastIndex) {
                    onAction(OnboardingEvent.OnFinish)
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            appearance = ButtonAppearance.Primary
        )
    }
}

data class OnboardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)

@Preview
@Composable
private fun PreviewOnboardingScreen() {
    OnboardingScreen(onAction = {})
}
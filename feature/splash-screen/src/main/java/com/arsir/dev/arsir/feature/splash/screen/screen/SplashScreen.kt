package com.arsir.dev.arsir.feature.splash.screen.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arsir.dev.arsir.uikit.R
import com.arsir.dev.arsir.uikit.theme.Typography

@Composable
internal fun SplashScreen(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color(0xFFCFEA82))
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 4.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_store),
                    contentDescription = null,
                )
                Text(
                    text = "Ar Store",
                    style = Typography.h5,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 18.sp,
                    letterSpacing = 0.08.sp
                )

            }
            AnimatedVisibility(isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(18.dp))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSplashScreen() {
    SplashScreen(isLoading = true)
}
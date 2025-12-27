package com.arsir.dev.arsir.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.uikit.shimmer.shimmerEffect

@Composable
internal fun ProductShimmer() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(10) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(2) {
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(Color.White)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .shimmerEffect()
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(12.dp)
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .shimmerEffect()
                            )

                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(12.dp)
                                    .height(32.dp)
                                    .width(64.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .shimmerEffect()
                            )
                        }

                        Spacer(Modifier.height(12.dp))

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .height(14.dp)
                                .fillMaxWidth(0.9f)
                                .clip(RoundedCornerShape(6.dp))
                                .shimmerEffect()
                        )

                        Spacer(Modifier.height(6.dp))

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .height(14.dp)
                                .fillMaxWidth(0.6f)
                                .clip(RoundedCornerShape(6.dp))
                                .shimmerEffect()
                        )

                        Spacer(Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(14.dp)
                                    .clip(CircleShape)
                                    .shimmerEffect()
                            )

                            Spacer(Modifier.width(6.dp))

                            Box(
                                modifier = Modifier
                                    .height(12.dp)
                                    .width(40.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .shimmerEffect()
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewProductCardShimmer() {
    ProductShimmer()
}
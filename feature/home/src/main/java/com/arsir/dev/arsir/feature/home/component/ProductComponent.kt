package com.arsir.dev.arsir.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arsir.dev.arsir.uikit.theme.Typography

@Composable
internal fun ProductImageSection(
    imageUrl: String,
    price: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFADADD),
                        Color(0xFFF5F5F5)
                    )
                )
            )
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.Fit
        )

        IconButton(
            onClick = onFavoriteClick,
            modifier = Modifier
                .padding(12.dp)
                .size(36.dp)
                .align(Alignment.TopEnd)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                imageVector = if (isFavorite)
                    Icons.Filled.Favorite
                else Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                tint = if (isFavorite) Color.Red else Color.Black
            )
        }

        Box(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomStart)
                .background(Color.Black, RoundedCornerShape(20.dp))
                .padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text(
                text = "$$price",
                color = Color.White,
                style = Typography.body2,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
internal fun ProductInfoSection(
    title: String,
    rate: Double,
    count: Int,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = "$rate",
                style = Typography.caption,
                color = Color.Black
            )
            Text(
                text = "($count)",
                style = Typography.caption,
                color = Color.Black.copy(
                    alpha = 0.8f
                ),
            )
        }
    }
}
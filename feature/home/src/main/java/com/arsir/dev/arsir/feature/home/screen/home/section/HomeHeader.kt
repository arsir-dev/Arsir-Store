package com.arsir.dev.arsir.feature.home.screen.home.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arsir.dev.arsir.uikit.circle.CircleIconButton
import com.arsir.dev.arsir.uikit.R as RUIKit

@Composable
internal fun HomeHeader(
    modifier: Modifier = Modifier,
    userName: String,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onBagClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GreetingPill(
            userName = userName,
            onClick = onProfileClick
        )
        Spacer(modifier = Modifier.weight(1f))
        NotificationButton(
            onClick = onNotificationClick
        )
        Spacer(modifier = Modifier.width(12.dp))
        CircleIconButton(
            icon = Icons.Outlined.ShoppingCart,
            contentDescription = "Bag",
            onClick = onBagClick
        )
    }
}

@Composable
private fun GreetingPill(
    userName: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF6F6F6))
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AvatarImage()
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Hey, $userName",
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
private fun AvatarImage(){
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(RUIKit.drawable.profile),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
private fun NotificationButton(
    onClick: () -> Unit
) {
    Box {
        CircleIconButton(
            icon = Icons.Outlined.Notifications,
            contentDescription = "Notification",
            onClick = onClick
        )
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(Color.Red, CircleShape)
                .align(Alignment.TopEnd)
                .offset(x = (-6).dp, y = 6.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewHomeHeader() {
    HomeHeader(
        userName = "Jobby",
        onProfileClick = {},
        onNotificationClick = {},
        onBagClick = {}
    )
}
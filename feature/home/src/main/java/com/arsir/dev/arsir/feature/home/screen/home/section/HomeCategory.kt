package com.arsir.dev.arsir.feature.home.screen.home.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arsir.dev.arsir.core.common.ext.orEmpty
import com.arsir.dev.arsir.feature.home.ext.Category
import com.arsir.dev.arsir.uikit.theme.Typography
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun HomeCategory(
    categories: PersistentList<Category?>,
    onCategory: (String) -> Unit = {},
    onSeeAllClick: () -> Unit= {},
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Category",
                style = Typography.h6
            )

            Text(
                text = "See All →",
                style = Typography.caption,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.clickable { onSeeAllClick() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.CenterHorizontally
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = categories,
                key = { it?.id.orEmpty() }
            ) { item ->
                CategoryItem(
                    category = item,
                    onClick = { onCategory(item?.id.orEmpty()) }
                )
            }
        }
    }
}

@Composable
private fun CategoryItem(
    category: Category?,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = category?.icon.orEmpty()),
                contentDescription = category?.title.orEmpty(),
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = category?.title.orEmpty(),
            style = Typography.body2,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 14.sp,
            letterSpacing = 0.01.sp
        )
    }
}

@Preview
@Composable
private fun PreviewHomeCategory() {
    HomeCategory(
        categories = persistentListOf(),
        onCategory = { },
        onSeeAllClick = {}
    )
}
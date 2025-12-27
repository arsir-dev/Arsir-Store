package com.arsir.dev.arsir.feature.home.screen.search.section

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
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
import com.arsir.dev.arsir.feature.home.screen.search.SearchEvent
import com.arsir.dev.arsir.uikit.circle.CircleIconButton
import com.arsir.dev.arsir.uikit.event.OnAction
import com.arsir.dev.arsir.uikit.textfield.AppTextField
import com.arsir.dev.arsir.uikit.textfield.TextFieldAppearance
import com.arsir.dev.arsir.uikit.theme.Typography
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun SearchHeader(
    query: String,
    onAction: OnAction,
    categories: PersistentList<Category?>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppTextField(
                value = query,
                onValueChange = { query ->
                    onAction(SearchEvent.OnValueChange(query = query))
                },
                appearance = TextFieldAppearance.Search,
                placeholder = "Search...",
                leadingIcon = Icons.Outlined.Search,
                modifier = Modifier.weight(1f)
            )
            CircleIconButton(
                icon = Icons.Outlined.Settings,
                contentDescription = null
            ) { }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
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
                    onClick = { onAction(SearchEvent.OnCategory(item?.id.orEmpty())) }
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
private fun PreviewSearch() {
    SearchHeader(
        query = "",
        onAction = {},
        categories = persistentListOf()
    )
}
package com.arsir.dev.arsir.feature.home.ext

import androidx.compose.runtime.Stable
import com.arsir.dev.arsir.uikit.R as RUIKit

internal fun String.toCategory(): Category? {
    return when (this.lowercase()) {
        "all" -> Category(
            id = this,
            title = "All",
            icon = RUIKit.drawable.ic_all
        )

        "men's clothing" -> Category(
            id = this,
            title = "Men",
            icon = RUIKit.drawable.ic_jacket
        )

        "women's clothing" -> Category(
            id = this,
            title = "Women",
            icon = RUIKit.drawable.ic_shirt
        )

        "electronics" -> Category(
            id = this,
            title = "Electronics",
            icon = RUIKit.drawable.ic_lamp
        )

        "jewelery" -> Category(
            id = this,
            title = "Jewelery",
            icon = RUIKit.drawable.ic_ring
        )

        else -> null
    }
}

@Stable
data class Category(
    val id: String,
    val title: String,
    val icon: Int,
)
package com.arsir.dev.arsir.data.product.implementation.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_favorite")
data class FavoriteEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,
)
package com.arsir.dev.arsir.data.product.implementation.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,

    val title: String = "",

    val price: Double = 0.0,

    val description: String = "",

    val category: String = "",

    val image: String = "",

    @Embedded(prefix = "rating_")
    val rating: RatingEntity = RatingEntity(),
)
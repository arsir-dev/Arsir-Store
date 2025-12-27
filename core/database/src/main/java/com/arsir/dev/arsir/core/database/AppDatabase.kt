package com.arsir.dev.arsir.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arsir.dev.arsir.data.product.implementation.database.dao.CartDao
import com.arsir.dev.arsir.data.product.implementation.database.dao.FavoriteDao
import com.arsir.dev.arsir.data.product.implementation.database.dao.ProductDao
import com.arsir.dev.arsir.data.product.implementation.database.entity.CartEntity
import com.arsir.dev.arsir.data.product.implementation.database.entity.FavoriteEntity
import com.arsir.dev.arsir.data.product.implementation.database.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        FavoriteEntity::class,
        CartEntity::class
    ],
    version = 7,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
    abstract fun favoriteDao() : FavoriteDao
    abstract fun cartDao() : CartDao
}
package com.arsir.dev.arsir.data.product.implementation.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arsir.dev.arsir.data.product.implementation.database.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM tbl_product")
    fun getProduct(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: List<ProductEntity>)

    @Query("DELETE FROM tbl_product")
    suspend fun deleteDao()
}
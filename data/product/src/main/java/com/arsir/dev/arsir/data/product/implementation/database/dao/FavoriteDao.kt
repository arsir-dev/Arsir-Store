package com.arsir.dev.arsir.data.product.implementation.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arsir.dev.arsir.data.product.implementation.database.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM tbl_favorite")
    fun getFavorite(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM tbl_favorite WHERE id = :productId LIMIT 1)")
    suspend fun isFavorite(productId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(product: FavoriteEntity)

    @Query("DELETE FROM tbl_favorite WHERE id = :productId")
    suspend fun deleteById(productId: Int)

    @Query("DELETE FROM tbl_favorite")
    suspend fun deleteAll()
}

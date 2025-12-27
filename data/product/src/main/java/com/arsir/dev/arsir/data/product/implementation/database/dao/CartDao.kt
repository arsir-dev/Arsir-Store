package com.arsir.dev.arsir.data.product.implementation.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arsir.dev.arsir.data.product.implementation.database.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM tbl_cart ORDER BY createdAt DESC")
    fun observeCart(): Flow<List<CartEntity>>

    @Query("SELECT * FROM tbl_cart WHERE id = :productId LIMIT 1")
    suspend fun getByProductId(productId: Int): CartEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(cart: CartEntity)

    @Query("""
        UPDATE tbl_cart
        SET isFavorite = :isFavorite
        WHERE id = :productId
    """)
    suspend fun updateFavorite(
        productId: Int,
        isFavorite: Boolean
    )

    @Query("""
        UPDATE tbl_cart
        SET quantity = quantity + 1
        WHERE id = :productId
    """)
    suspend fun increaseQuantity(productId: Int)

    @Query("""
        UPDATE tbl_cart
        SET quantity = quantity - 1
        WHERE id = :productId AND quantity > 1
    """)
    suspend fun decreaseQuantity(productId: Int)

    @Query("DELETE FROM tbl_cart WHERE id = :productId")
    suspend fun delete(productId: Int)

    @Query("SELECT SUM(quantity) FROM tbl_cart")
    fun observeTotalItem(): Flow<Int>

    @Query("SELECT SUM(price * quantity) FROM tbl_cart")
    fun observeTotalPrice(): Flow<Double>
}
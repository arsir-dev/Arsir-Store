package com.arsir.dev.arsir.domain.product.repository

import com.arsir.dev.arsir.core.common.StoreResponse
import com.arsir.dev.arsir.domain.product.model.Cart
import com.arsir.dev.arsir.domain.product.model.Favorite
import com.arsir.dev.arsir.domain.product.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun product(): StoreResponse<List<Product>>

    fun getFavorite(): Flow<List<Favorite>>

    suspend fun getFavoriteById(productId: Int): Boolean

    suspend fun insertFavoriteById(favorite: Favorite)

    suspend fun deleteFavoriteById(productId: Int)

    suspend fun getItem(productId: Int): Cart?

    suspend fun upsert(item: Cart)

    suspend fun increase(productId: Int)

    suspend fun decrease(productId: Int)

    suspend fun remove(productId: Int)

    suspend fun updateFavoriteCart(isFavorite: Boolean, productId: Int)

    fun observeCart(): Flow<List<Cart>>

    fun observeTotalItem(): Flow<Int>

    fun observeTotalPrice(): Flow<Double>
}
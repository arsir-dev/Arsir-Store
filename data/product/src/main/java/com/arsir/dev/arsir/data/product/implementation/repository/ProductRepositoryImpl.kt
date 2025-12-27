package com.arsir.dev.arsir.data.product.implementation.repository

import com.arsir.dev.arsir.core.common.StoreResponse
import com.arsir.dev.arsir.core.remote.ext.fetching
import com.arsir.dev.arsir.core.remote.response.ApiResult
import com.arsir.dev.arsir.data.product.implementation.database.dao.CartDao
import com.arsir.dev.arsir.data.product.implementation.database.dao.FavoriteDao
import com.arsir.dev.arsir.data.product.implementation.database.dao.ProductDao
import com.arsir.dev.arsir.data.product.implementation.database.entity.CartEntity
import com.arsir.dev.arsir.data.product.implementation.database.entity.FavoriteEntity
import com.arsir.dev.arsir.data.product.implementation.mapper.local.toCart
import com.arsir.dev.arsir.data.product.implementation.mapper.local.toCartEntity
import com.arsir.dev.arsir.data.product.implementation.mapper.local.toFavorite
import com.arsir.dev.arsir.data.product.implementation.mapper.local.toFavoriteEntity
import com.arsir.dev.arsir.data.product.implementation.mapper.local.toProductEntity
import com.arsir.dev.arsir.data.product.implementation.mapper.remote.toProduct
import com.arsir.dev.arsir.data.product.implementation.remote.api.ProductApi
import com.arsir.dev.arsir.domain.product.model.Cart
import com.arsir.dev.arsir.domain.product.model.Favorite
import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.domain.product.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val cartDao: CartDao,
    private val productDao: ProductDao,
    private val favoriteDao: FavoriteDao,
    private val ioDispatcher: CoroutineDispatcher,
): ProductRepository {
    override suspend fun product(): StoreResponse<List<Product>> = withContext(ioDispatcher) {
        val apiResult = fetching {
            productApi.product()
        }

        when(apiResult) {
            is ApiResult.Success -> {
                val product = apiResult
                    .data
                    ?.map { it.toProduct() }.orEmpty()

                val productEntity = apiResult
                    .data
                    ?.map { it.toProductEntity() }.orEmpty()

                productDao.apply {
                    deleteDao()
                    insertProduct(product = productEntity)
                }

                StoreResponse.Success(data = product)
            }
            is ApiResult.Error -> {
                StoreResponse.Error(message = apiResult.message)
            }
        }
    }

    override fun getFavorite(): Flow<List<Favorite>> {
        return favoriteDao.getFavorite().map { it.map(FavoriteEntity::toFavorite) }
    }

    override suspend fun getFavoriteById(productId: Int): Boolean = withContext(ioDispatcher) {
        favoriteDao.isFavorite(productId = productId)
    }

    override suspend fun insertFavoriteById(favorite: Favorite) = withContext(ioDispatcher) {
        favoriteDao.insertFavorite(favorite.toFavoriteEntity())
    }

    override suspend fun deleteFavoriteById(productId: Int) = withContext(ioDispatcher) {
        favoriteDao.deleteById(productId = productId)
    }

    override suspend fun getItem(productId: Int): Cart? = withContext(ioDispatcher) {
        cartDao.getByProductId(productId)?.toCart()
    }

    override suspend fun upsert(item: Cart) = withContext(ioDispatcher) {
        cartDao.upsert(item.toCartEntity())
    }

    override suspend fun increase(productId: Int) = withContext(ioDispatcher) {
        cartDao.increaseQuantity(productId)
    }

    override suspend fun decrease(productId: Int) = withContext(ioDispatcher) {
        cartDao.decreaseQuantity(productId)
    }

    override suspend fun remove(productId: Int) = withContext(ioDispatcher) {
        cartDao.delete(productId)
    }

    override suspend fun updateFavoriteCart(isFavorite: Boolean, productId: Int) {
        cartDao.updateFavorite(isFavorite = isFavorite, productId = productId)
    }

    override fun observeCart(): Flow<List<Cart>> {
        return cartDao.observeCart().map { it.map(CartEntity::toCart) }
    }

    override fun observeTotalItem(): Flow<Int> {
        return cartDao.observeTotalItem()
    }

    override fun observeTotalPrice(): Flow<Double> {
        return cartDao.observeTotalPrice()
    }
}
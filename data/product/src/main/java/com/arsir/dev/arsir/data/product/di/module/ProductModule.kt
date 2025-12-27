package com.arsir.dev.arsir.data.product.di.module

import com.arsir.dev.arsir.core.coroutines.qualifier.IoDispatcher
import com.arsir.dev.arsir.core.network.qualifier.Api
import com.arsir.dev.arsir.data.product.implementation.database.dao.CartDao
import com.arsir.dev.arsir.data.product.implementation.database.dao.FavoriteDao
import com.arsir.dev.arsir.data.product.implementation.database.dao.ProductDao
import com.arsir.dev.arsir.data.product.implementation.remote.api.ProductApi
import com.arsir.dev.arsir.data.product.implementation.repository.ProductRepositoryImpl
import com.arsir.dev.arsir.domain.product.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProductModule {

    @Provides
    @Singleton
    fun provideProductApi(@Api retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    fun provideProductRepository(
        productApi: ProductApi,
        cartDao: CartDao,
        productDao: ProductDao,
        favoriteDao: FavoriteDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): ProductRepository {
        return ProductRepositoryImpl(
            productApi = productApi,
            cartDao = cartDao,
            productDao = productDao,
            favoriteDao = favoriteDao,
            ioDispatcher = ioDispatcher,
        )
    }
}
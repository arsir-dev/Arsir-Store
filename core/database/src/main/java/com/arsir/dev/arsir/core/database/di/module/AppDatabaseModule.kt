package com.arsir.dev.arsir.core.database.di.module

import android.content.Context
import androidx.room.Room
import com.arsir.dev.arsir.core.database.AppDatabase
import com.arsir.dev.arsir.data.product.implementation.database.dao.CartDao
import com.arsir.dev.arsir.data.product.implementation.database.dao.FavoriteDao
import com.arsir.dev.arsir.data.product.implementation.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room
        .databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "app_database"
        )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideProductDao(
        database: AppDatabase
    ): ProductDao = database.productDao()

    @Provides
    fun provideFavoriteDao(
        database: AppDatabase
    ): FavoriteDao = database.favoriteDao()

    @Provides
    fun provideCartDao(
        database: AppDatabase
    ): CartDao = database.cartDao()
}
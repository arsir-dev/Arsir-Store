package com.arsir.dev.arsir.data.splash.screen.di.domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.arsir.dev.arsir.core.coroutines.qualifier.IoDispatcher
import com.arsir.dev.arsir.core.datastore.qualifier.AppDataStore
import com.arsir.dev.arsir.data.splash.screen.implementation.datastore.SplashDataStoreImpl
import com.arsir.dev.arsir.data.splash.screen.implementation.datastore.SplashDataStore
import com.arsir.dev.arsir.data.splash.screen.implementation.repository.SplashRepositoryImpl
import com.arsir.dev.arsir.domain.splash.screen.repository.SplashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SplashModule {

    @Provides
    @Singleton
    fun provideSplashDataStore(
        @AppDataStore dataStore: DataStore<Preferences>,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): SplashDataStore {
        return SplashDataStoreImpl(
            dataStore = dataStore,
            ioDispatcher = ioDispatcher,
        )
    }

    @Provides
    fun provideSplashRepository(
        splashDataStore: SplashDataStore
    ): SplashRepository {
        return SplashRepositoryImpl(splashDataStore = splashDataStore)
    }
}
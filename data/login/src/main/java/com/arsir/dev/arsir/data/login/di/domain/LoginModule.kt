package com.arsir.dev.arsir.data.login.di.domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.arsir.dev.arsir.core.coroutines.qualifier.IoDispatcher
import com.arsir.dev.arsir.core.datastore.qualifier.AppDataStore
import com.arsir.dev.arsir.core.network.qualifier.Api
import com.arsir.dev.arsir.data.login.implementation.datastore.LoginDataStore
import com.arsir.dev.arsir.data.login.implementation.datastore.LoginDataStoreImpl
import com.arsir.dev.arsir.data.login.implementation.remote.api.LoginApi
import com.arsir.dev.arsir.data.login.implementation.repository.LoginRepositoryImpl
import com.arsir.dev.arsir.domain.login.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LoginModule {

    @Provides
    @Singleton
    fun provideLoginApi(@Api retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginDataStore(
        @AppDataStore dataStore: DataStore<Preferences>,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): LoginDataStore {
        return LoginDataStoreImpl(
            dataStore = dataStore,
            ioDispatcher = ioDispatcher,
        )
    }

    @Provides
    fun provideLoginRepository(
        loginApi: LoginApi,
        loginDataStore: LoginDataStore,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): LoginRepository {
        return LoginRepositoryImpl(
            loginApi = loginApi,
            loginDataStore = loginDataStore,
            ioDispatcher = ioDispatcher,
        )
    }
}
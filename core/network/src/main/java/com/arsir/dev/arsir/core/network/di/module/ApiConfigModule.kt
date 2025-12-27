package com.arsir.dev.arsir.core.network.di.module

import com.arsir.dev.arsir.core.network.config.ApiConfig
import com.arsir.dev.arsir.core.network.qualifier.Api
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiConfigModule {

    @Provides
    fun provideMoshi(): Moshi = ApiConfig.provideMoshi()

    @Provides
    fun provideHttpClient(): OkHttpClient.Builder = ApiConfig.provideHttpClient()

    @Provides
    @Singleton
    @Api
    fun provideRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient.Builder,
    ): Retrofit =ApiConfig.provideRetrofit(
        moshi = moshi,
        okHttpClient = okHttpClient,
    )
}
package com.arsir.dev.arsir.core.network.config

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

internal object ApiConfig {
    private const val RETROFIT_TIMEOUT: Long = 300

    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun provideHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
            .cache(null)
    }

    fun provideRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient.Builder,
    ): Retrofit {
        val okHttpBuild = okHttpClient
            .retryOnConnectionFailure(true)
            .followRedirects(true)
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .build()
                it.proceed(request)
            }
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .build()

        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpBuild)
            .build()
    }
}
package com.arsir.dev.arsir.feature.detail.di.module

import com.arsir.dev.arsir.feature.detail.navigation.DetailNavigationImpl
import com.arsir.dev.arsir.navigation.DetailNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DetailModule {
    @Binds
    fun bindDetailNavigation(
        detailNavigationImpl: DetailNavigationImpl
    ): DetailNavigation
}
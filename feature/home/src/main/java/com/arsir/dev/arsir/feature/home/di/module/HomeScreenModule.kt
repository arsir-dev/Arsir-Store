package com.arsir.dev.arsir.feature.home.di.module

import com.arsir.dev.arsir.feature.home.navigation.HomeNavigationImpl
import com.arsir.dev.arsir.navigation.HomeNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface HomeScreenModule {

    @Binds
    fun bindHomeNavigation(
        homeNavigationImpl: HomeNavigationImpl
    ): HomeNavigation
}
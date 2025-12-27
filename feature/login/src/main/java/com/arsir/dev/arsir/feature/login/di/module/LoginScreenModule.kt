package com.arsir.dev.arsir.feature.login.di.module

import com.arsir.dev.arsir.feature.login.navigation.LoginNavigationImpl
import com.arsir.dev.arsir.navigation.LoginNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LoginScreenModule {

    @Binds
    fun bindLoginNavigation(
        loginNavigationImpl: LoginNavigationImpl
    ): LoginNavigation
}
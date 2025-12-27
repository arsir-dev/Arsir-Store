package com.arsir.dev.arsir.data.login.implementation.repository

import com.arsir.dev.arsir.core.common.StoreResponse
import com.arsir.dev.arsir.core.remote.ext.fetching
import com.arsir.dev.arsir.core.remote.response.ApiResult
import com.arsir.dev.arsir.data.login.implementation.datastore.LoginDataStore
import com.arsir.dev.arsir.data.login.implementation.mapper.toLogin
import com.arsir.dev.arsir.data.login.implementation.remote.api.LoginApi
import com.arsir.dev.arsir.data.login.implementation.remote.request.LoginRequest
import com.arsir.dev.arsir.domain.login.model.Login
import com.arsir.dev.arsir.domain.login.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val loginDataStore: LoginDataStore,
    private val ioDispatcher: CoroutineDispatcher
): LoginRepository {

    override suspend fun login(
        username: String,
        password: String,
    ): StoreResponse<Login> = withContext(ioDispatcher) {
        val loginRequest = LoginRequest(
            username = username,
            password = password
        )

        val apiResult = fetching {
            loginApi.login(request = loginRequest)
        }

        when(apiResult) {
            is ApiResult.Success -> {
                val login = apiResult.data?.toLogin() ?: Login()
                loginDataStore.setToken(token = login.token)
                StoreResponse.Success(data = login)
            }
            is ApiResult.Error -> {
                StoreResponse.Error(apiResult.message)
            }
        }
    }
}
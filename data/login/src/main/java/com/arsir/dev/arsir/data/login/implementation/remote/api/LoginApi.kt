package com.arsir.dev.arsir.data.login.implementation.remote.api

import com.arsir.dev.arsir.data.login.implementation.remote.request.LoginRequest
import com.arsir.dev.arsir.data.login.implementation.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface LoginApi {

    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}
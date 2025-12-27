package com.arsir.dev.arsir.data.product.implementation.remote.api

import com.arsir.dev.arsir.data.product.implementation.remote.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

internal interface ProductApi {

    @Headers("Content-Type: application/json")
    @GET("/products")
    suspend fun product(): Response<List<ProductResponse>>
}
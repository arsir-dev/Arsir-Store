package com.arsir.dev.arsir.core.remote.ext

import com.arsir.dev.arsir.core.remote.response.ApiResponse
import com.arsir.dev.arsir.core.remote.response.ApiResult
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T: Any> fetching(
    remoteApi: suspend () -> Response<T>,
): ApiResult<T> = try {
    val response = remoteApi()
    val body = response.body()

    if (response.isSuccessful && body != null) {
        ApiResult.Success(
            data = body,
            code = response.code(),
            message = response.message(),
        )
    } else {
        ApiResult.Error(
            codeResponse = response.code(),
            message = response.errorBody()?.source()?.readUtf8() ?: "Opss, Mohon maaf terjadi kesalahan"
        )
    }
} catch (e: HttpException) {
    ApiResult.Error(e.code(), e.message.orEmpty())
} catch (e: IOException) {
    ApiResult.Error(500, e.message.orEmpty())
} catch (e: UnknownHostException) {
    ApiResult.Error(500, e.message.orEmpty())
} catch (e: Exception) {
    ApiResult.Error(400, e.message.orEmpty())
}

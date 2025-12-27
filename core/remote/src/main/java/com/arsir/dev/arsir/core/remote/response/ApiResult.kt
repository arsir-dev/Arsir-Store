package com.arsir.dev.arsir.core.remote.response

sealed class ApiResult<out T> {
    data class Success<out T>(
        val data: T? = null,
        val code: Int = 0,
        val message: String = "",
    ) : ApiResult<T>()

    data class Error(
        val codeResponse: Int,
        val message: String,
    ) : ApiResult<Nothing>()
}
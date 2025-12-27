package com.arsir.dev.arsir.core.common

sealed interface StoreResponse<out T> {
    open class Error(open val message: String) : StoreResponse<Nothing>
    data class Success<T>(
        val data: T,
        val code: String = "",
        val message: String = "",
    ) : StoreResponse<T>
}
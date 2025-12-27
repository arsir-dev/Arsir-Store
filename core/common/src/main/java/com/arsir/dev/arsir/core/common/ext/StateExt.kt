package com.arsir.dev.arsir.core.common.ext

import com.arsir.dev.arsir.core.common.StoreResponse

fun <T> StoreResponse<T>.isSuccess(): Boolean = this is StoreResponse.Success

fun <T> StoreResponse<T>.isError(): Boolean = this is StoreResponse.Error

fun <T> StoreResponse<T>.data(): T? = (this as? StoreResponse.Success)?.data

fun <T> StoreResponse<T>.message(): String = (this as? StoreResponse.Error)?.message.orEmpty()

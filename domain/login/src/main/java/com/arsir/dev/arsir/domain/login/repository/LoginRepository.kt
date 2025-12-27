package com.arsir.dev.arsir.domain.login.repository

import com.arsir.dev.arsir.core.common.StoreResponse
import com.arsir.dev.arsir.domain.login.model.Login

fun interface LoginRepository {
    suspend fun login(
        username: String,
        password: String,
    ): StoreResponse<Login>
}
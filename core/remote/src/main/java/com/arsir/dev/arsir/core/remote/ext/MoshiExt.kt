package com.arsir.dev.arsir.core.remote.ext

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun <T: Any> T.toJson(): String {
    return when (this) {
        is List<*>, String -> moshi.adapter<Any>(
            Types.newParameterizedType(List::class.java, Any::class.java))
            .toJson(this)
        is Map<*,*> -> moshi.adapter<Any>(
            Types.newParameterizedType(
                MutableMap::class.java,
                Any::class.java,
                Any::class.java
            ))
            .toJson(this)
        else -> moshi.adapter(Any::class.java).toJson(this)
    }
}

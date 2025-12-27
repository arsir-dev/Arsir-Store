package com.arsir.dev.arsir.core.common.ext

fun Int?.orEmpty(): Int {
    return this ?: 0
}

fun Long?.orEmpty(): Long {
    return this ?: 0
}

fun Boolean?.orEmpty(): Boolean {
    return this ?: false
}
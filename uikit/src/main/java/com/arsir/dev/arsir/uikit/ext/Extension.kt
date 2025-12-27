package com.arsir.dev.arsir.uikit.ext

suspend inline fun <T> runWithLoading(
    setLoading: (Boolean) -> Unit,
    crossinline block: suspend () -> T
): T {
    setLoading(true)
    return try {
        block()
    } finally {
        setLoading(false)
    }
}

fun Double.round(): Double = String.format("%.2f", this).toDouble()
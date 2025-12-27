package com.arsir.dev.arsir.feature.splash.screen.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal fun CoroutineScope.runWithLoadingDelay(
    delayMillis: Long,
    onStart: suspend () -> Unit,
    onFinish: suspend () -> Unit,
    onEvent: (suspend () -> Unit)? = null,
) = launch {
    onStart()
    delay(delayMillis)
    onFinish()
    onEvent?.invoke()
}
package com.arsir.dev.arsir.observer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

inline fun <S> LifecycleOwner.observeState(
    source: Flow<S>,
    minState: Lifecycle.State = Lifecycle.State.RESUMED,
    context: CoroutineContext = Dispatchers.Main,
    crossinline action: suspend (newState: S) -> Unit,
) {
    lifecycleScope.launch(context) {
        repeatOnLifecycle(minState) {
            source.collect { state ->
                action(state)
            }
        }
    }
}
package com.arsir.dev.arsir.feature.splash.screen.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsir.dev.arsir.domain.splash.screen.repository.SplashRepository
import com.arsir.dev.arsir.feature.splash.screen.ext.runWithLoadingDelay
import com.arsir.dev.arsir.uikit.event.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashRepository: SplashRepository
): ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state get() = _state.asStateFlow()

    private val _effect = Channel<SplashEffect>(Channel.BUFFERED)
    val effect get() = _effect.receiveAsFlow()

    fun onEvent(event: UiEvent) {
        when(event) {
            SplashEvent.OnLoad -> onLoad()
        }
    }

    private fun onLoad() = viewModelScope.runWithLoadingDelay(
        delayMillis = Random.nextLong(from = 1_000L, until = 3_000L),
        onStart ={
            _state.update { it.copy(isLoading = true) }
        },
        onFinish = {
            _state.update { it.copy(isLoading= false) }
        },
        onEvent = {
            val isRunOnce = splashRepository
                .getRunOnce()
                .first()
            val onEffect = if (isRunOnce) {
                SplashEffect.Login
            } else {
                SplashEffect.Onboarding
            }

            _effect.trySend(onEffect)
        }
    )
}
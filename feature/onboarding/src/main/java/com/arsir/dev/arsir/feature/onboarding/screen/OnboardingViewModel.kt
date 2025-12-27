package com.arsir.dev.arsir.feature.onboarding.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsir.dev.arsir.domain.onboarding.repository.OnboardingRepository
import com.arsir.dev.arsir.uikit.event.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
): ViewModel() {
    private val _effect = Channel<OnboardingEffect>(Channel.BUFFERED)
    val effect get() = _effect.receiveAsFlow()

    fun onEvent(event: UiEvent){
        when(event) {
           is OnboardingEvent.OnFinish -> onSetRunOnce()
        }
    }

    private fun onSetRunOnce() = viewModelScope.launch {
        onboardingRepository.setRunOnce()
        _effect.send(OnboardingEffect.OnFinish)
    }
}
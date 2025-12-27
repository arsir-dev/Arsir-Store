package com.arsir.dev.arsir.feature.login.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsir.dev.arsir.core.common.StoreResponse
import com.arsir.dev.arsir.core.common.ext.isSuccess
import com.arsir.dev.arsir.domain.login.model.Login
import com.arsir.dev.arsir.domain.login.repository.LoginRepository
import com.arsir.dev.arsir.uikit.event.UiEvent
import com.arsir.dev.arsir.uikit.ext.runWithLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state get() = _state.asStateFlow()

    private val _effect = Channel<LoginEffect>(Channel.BUFFERED)
    val effect get() = _effect.receiveAsFlow()

    fun onEvent(event: UiEvent) {
        when (event) {
            is LoginEvent.OnUsername -> onUpdateUsername(username = event.username)
            is LoginEvent.OnPassword -> onUpdatePassword(password = event.password)
            is LoginEvent.OnLogin -> {
                val usernameError =
                    if (_state.value.isUsernameBlank) "Username tidak boleh kosong" else null

                val passwordError =
                    if (_state.value.isPasswordBlank) "Password tidak boleh kosong" else null

                if (usernameError != null || passwordError != null) {
                    _state.update {
                        it.copy(
                            usernameError = usernameError,
                            passwordError = passwordError
                        )
                    }
                    return
                }

                login(username = event.username, password = event.password)
            }
        }
    }

    private fun onUpdateUsername(username: String) {
        _state.update {
            it.copy(
                username = username,
                usernameError = null,
            )
        }
    }

    private fun onUpdatePassword(password: String) {
        _state.update {
            it.copy(
                password = password,
                passwordError = null,
            )
        }
    }

    private fun login(username: String, password: String) = viewModelScope.launch {
        val response = runWithLoading(
            setLoading = { isLoading ->
                _state.update { it.copy(isLoading = isLoading) }
            },
            block = {
                loginRepository.login(username = username, password = password)
            }
        )
        handleResponse(response = response)
    }

    private fun handleResponse(response: StoreResponse<Login>) {
        val onEffect = if (response.isSuccess()) {
            LoginEffect.OnHome
        } else {
            LoginEffect.OnError(message = (response as StoreResponse.Error).message)
        }
        _effect.trySend(onEffect)
    }
}
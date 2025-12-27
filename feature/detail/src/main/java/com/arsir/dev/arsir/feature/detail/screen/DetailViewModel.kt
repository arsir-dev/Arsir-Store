package com.arsir.dev.arsir.feature.detail.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsir.dev.arsir.core.common.StoreResponse
import com.arsir.dev.arsir.core.common.ext.data
import com.arsir.dev.arsir.core.common.ext.isError
import com.arsir.dev.arsir.core.common.ext.message
import com.arsir.dev.arsir.domain.product.model.Cart
import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.domain.product.repository.ProductRepository
import com.arsir.dev.arsir.uikit.event.UiEvent
import com.arsir.dev.arsir.uikit.ext.runWithLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state get() = _state.asStateFlow()

    private val _effect = Channel<DetailEffect>(Channel.BUFFERED)
    val effect get() = _effect.receiveAsFlow()

    private val productId: Int =
        savedStateHandle["productId"]
            ?: error("productId is required")

    fun onEvent(event: UiEvent) {
        when (event) {
            is DetailEvent.OnBack -> sendEffect(DetailEffect.OnBack)
            is DetailEvent.OnUpsert -> onUpset(product = event.product)
            is DetailEvent.OnIncrease -> onIncrease()
            is DetailEvent.OnDecrease -> onDecrease()
        }
    }

    init {
        fetchingProduct()
    }

    private fun fetchingProduct() = viewModelScope.launch {
        val response = runWithLoading(
            setLoading = { isLoading ->
                _state.update { it.copy(isLoading = isLoading) }
            },
            block = {
                productRepository.product()
            }
        )
        handleResponse(response = response)
    }

    private fun handleResponse(response: StoreResponse<List<Product>>) {
        if (response.isError()) {
            sendEffect(DetailEffect.OnGeneralError(message = response.message()))
            return
        }

        val data = response.data().orEmpty()
        val product = data
            .find { it.id == productId }

        _state.update { it.copy(product = product ?: Product()) }
    }

    private fun onUpset(product: Product) = viewModelScope.launch {
        val cart = runWithLoading(
            setLoading = { isLoading ->
                sendEffect(DetailEffect.OnLoading(isLoading = isLoading))
            },
            block = {
                val isFavorite = productRepository.getFavoriteById(productId = productId)
                delay(Random.nextLong(from = 3_00, until = 5_00))
                Cart(
                    id = product.id,
                    title = product.title,
                    price = _state.value.totalPrice,
                    description = product.description,
                    category = product.category,
                    image = product.image,
                    quantity = _state.value.quantity,
                    isFavorite = isFavorite,
                    rating = product.rating,
                )
            }
        )
        productRepository.upsert(item = cart).run {
            sendEffect(DetailEffect.OnSuccess)
        }
    }

    private fun onIncrease() = viewModelScope.launch {
        _state.update { currentState ->
            currentState.copy(
                quantity = currentState.quantity + 1,
            )
        }
    }

    private fun onDecrease() = viewModelScope.launch {
        _state.update { currentState ->
            val newQuantity = (currentState.quantity - 1).coerceAtLeast(0)
            currentState.copy(quantity = newQuantity)
        }
    }

    private fun sendEffect(effect: DetailEffect) = viewModelScope.launch {
        _effect.send(effect)
    }
}
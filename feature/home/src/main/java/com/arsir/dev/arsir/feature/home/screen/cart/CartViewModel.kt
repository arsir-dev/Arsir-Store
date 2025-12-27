package com.arsir.dev.arsir.feature.home.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsir.dev.arsir.domain.product.model.Cart
import com.arsir.dev.arsir.domain.product.model.Favorite
import com.arsir.dev.arsir.domain.product.repository.ProductRepository
import com.arsir.dev.arsir.uikit.event.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val _state = MutableStateFlow(CartState())
    val state get() = _state.asStateFlow()

    private val _effect = Channel<CartEffect>(Channel.BUFFERED)
    val effect get() = _effect.receiveAsFlow()

    init {
        observerCart()
        observerSearch()
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            is CartEvent.OnBack -> sendEffect(CartEffect.OnBack)
            is CartEvent.OnRemove -> onRemove(productId = event.productId)
            is CartEvent.OnIncrease -> onIncrease(productId = event.productId)
            is CartEvent.OnDecrease -> onDecrease(productId = event.productId)
            is CartEvent.OnValueChange -> onQueryChange(query = event.query)
            is CartEvent.OnFavorite -> onFavorite(cart = event.cart)
            is CartEvent.OnCheckout -> sendEffect(CartEffect.OnCheckout(subTotal = event.subTotal))
        }
    }

    private fun onQueryChange(query: String) {
        _query.value = query
        _state.update { it.copy(query = query) }
    }

    private fun observerCart() = viewModelScope.launch {
        combine(
            productRepository.observeCart(),
            productRepository.observeTotalPrice(),
        ) { cart, price ->
            CartState(
                cart = cart,
                carts = cart,
                price = price,
            )
        }
            .distinctUntilChanged()
            .collect { newState ->
                _state.value = newState
            }
    }

    private fun observerSearch() {
        _query
            .debounce(300)
            .distinctUntilChanged()
            .onEach { query ->
                filterCart(query = query)
            }
            .launchIn(viewModelScope)
    }

    private fun filterCart(query: String) = viewModelScope.launch {
        val source = _state.value.cart

        if (query.isBlank()) {
            _state.update { currentState ->
                currentState.copy(
                    carts = source.map { cart ->
                        val favoriteId = productRepository.getFavoriteById(productId = cart.id)
                        cart.copy(
                            isFavorite = favoriteId
                        )
                    }
                )
            }
            return@launch
        }

        val filtered = source.filter { cart ->
            cart.title.contains(
                query, ignoreCase = true
            ) || cart.category.contains(
                query, ignoreCase = true
            )
        }

        _state.update { currentState ->
            currentState.copy(
                carts = filtered.map { cart ->
                    val favoriteId = productRepository.getFavoriteById(productId = cart.id)
                    cart.copy(
                        isFavorite = favoriteId
                    )
                }
            )
        }
    }

    private fun onFavorite(cart: Cart) = viewModelScope.launch {
        productRepository.insertFavoriteById(
            favorite = Favorite(
                id = cart.id,
                isFavorite = cart.isFavorite
            )
        )
        productRepository.updateFavoriteCart(
            productId = cart.id,
            isFavorite = !cart.isFavorite,
        )
        _state.update { currentState ->
            val carts = currentState.carts.map { item ->
                if (item.id == cart.id) {
                    item.copy(isFavorite = !cart.isFavorite)
                } else {
                    item
                }
            }
            currentState.copy(carts = carts)
        }
    }

    private fun onIncrease(productId: Int) = viewModelScope.launch {
        productRepository.increase(productId = productId)
    }

    private fun onDecrease(productId: Int) = viewModelScope.launch {
        productRepository.decrease(productId = productId)
    }

    private fun onRemove(productId: Int) = viewModelScope.launch {
        productRepository.remove(productId)
    }

    private fun sendEffect(effect: CartEffect) = viewModelScope.launch {
        _effect.send(effect)
    }

}
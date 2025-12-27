package com.arsir.dev.arsir.feature.home.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsir.dev.arsir.core.common.StoreResponse
import com.arsir.dev.arsir.core.common.ext.data
import com.arsir.dev.arsir.core.common.ext.isError
import com.arsir.dev.arsir.core.common.ext.message
import com.arsir.dev.arsir.domain.product.model.Favorite
import com.arsir.dev.arsir.domain.product.model.Product
import com.arsir.dev.arsir.domain.product.repository.ProductRepository
import com.arsir.dev.arsir.feature.home.ext.Category
import com.arsir.dev.arsir.feature.home.ext.toCategory
import com.arsir.dev.arsir.uikit.event.UiEvent
import com.arsir.dev.arsir.uikit.ext.runWithLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val _state = MutableStateFlow(SearchState())
    val state get() = _state.asStateFlow()

    private val _effect = Channel<SearchEffect>(Channel.BUFFERED)
    val effect get() = _effect.receiveAsFlow()

    init {
        observeSearch()
        observeFavorite()
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            is SearchEvent.OnFetching -> fetchProduct()
            is SearchEvent.OnCategory -> viewModelScope.launch {
                val product = runWithLoading(
                    setLoading = { isLoading ->
                        _state.update { it.copy(isShimmer = isLoading) }
                    },
                    block = {
                        delay(Random.nextLong(from = 3_00, until = 5_00))
                        if (event.category == ALL) {
                            _state.value.product
                                .shuffled()
                                .map { product ->
                                    product.copy(
                                        isFavorite = _state.value.favoriteId.contains(product.id)
                                    )
                                }
                        } else {
                            _state.value.product
                                .filter { it.category == event.category }
                                .map { product ->
                                    product.copy(
                                        isFavorite = _state.value.favoriteId.contains(product.id)
                                    )
                                }
                        }
                    }
                )
                _state.update { it.copy(products = product) }
            }
            is SearchEvent.OnFavorite -> onFavorite(product = event.product)
            is SearchEvent.OnValueChange -> onQueryChange(text = event.query)
            is SearchEvent.OnDetail -> sendEffect(SearchEffect.OnDetail(productId = event.productId))
        }
    }

    private fun onQueryChange(text: String) {
        _query.value = text
        _state.update { it.copy(query = text) }
    }

    private fun observeFavorite() = viewModelScope.launch {
        productRepository.getFavorite().collect { favoriteId ->
            _state.update { currentState ->
                currentState.copy(favoriteId = favoriteId.map { it.id }.toSet())
            }
        }
    }

    private fun observeSearch() {
        _query
            .debounce(300)
            .distinctUntilChanged()
            .onEach { query ->
                filterProduct(query)
            }
            .launchIn(viewModelScope)
    }

    private fun filterProduct(query: String) = viewModelScope.launch {
        val source = _state.value.product
        if (query.isBlank()) {
            _state.update { currentState ->
                currentState.copy(
                    products = source.map { product ->
                        product.copy(
                            isFavorite = _state.value.favoriteId.contains(product.id)
                        )
                    }
                )
            }
            return@launch
        }

        val filtered = source.filter { product ->
            product.title.contains(
                query, ignoreCase = true
            ) || product.category.contains(
                query, ignoreCase = true
            )
        }

        _state.update { currentState ->
            currentState.copy(
                products = filtered.map { product ->
                    product.copy(
                        isFavorite = _state.value.favoriteId.contains(product.id)
                    )
                }
            )
        }
    }

    private fun onFavorite(product: Product) = viewModelScope.launch {
        productRepository.insertFavoriteById(
            favorite = Favorite(
                id = product.id,
                isFavorite = !product.isFavorite
            )
        )
        _state.update { currentState ->
            val currentProduct = currentState.products.map { item ->
                if (item.id == product.id) {
                    item.copy(isFavorite = !product.isFavorite)
                } else {
                    item
                }
            }
            currentState.copy(products = currentProduct)
        }
    }

    private fun fetchProduct() = viewModelScope.launch {
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
            sendEffect(SearchEffect.OnGeneralError(message = response.message()))
            return
        }

        val products = response.data().orEmpty()
        val categories = listOf("all") + products
            .map { it.category }
            .distinct()
        val category = categories.map { it.toCategory() }

        setProduct(products = products)
        setCategory(category = category)
    }

    private fun setProduct(products: List<Product>) {
        val product = products
            .shuffled(Random(seed))
            .distinct()

        _state.update {
            it.copy(
                product = product,
                products = product
            )
        }
    }

    private fun setCategory(category: List<Category?>) {
        _state.update {
            it.copy(category = category)
        }
    }

    private fun sendEffect(effect: SearchEffect) = viewModelScope.launch {
        _effect.send(effect)
    }

    private companion object {
        const val ALL = "all"

        val seed: Int = System.currentTimeMillis().toInt()
    }
}
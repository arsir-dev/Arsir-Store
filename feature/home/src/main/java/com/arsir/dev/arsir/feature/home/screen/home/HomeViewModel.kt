package com.arsir.dev.arsir.feature.home.screen.home

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
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    private val _effect = Channel<HomeEffect>(Channel.BUFFERED)
    val effect get() = _effect.receiveAsFlow()

    fun onEvent(event: UiEvent) {
        when (event) {
            is HomeEvent.OnFetching, HomeEvent.OnRefresh -> fetchProduct()
            is HomeEvent.OnCategory -> viewModelScope.launch {
                val popular = runWithLoading(
                    setLoading = { isLoading ->
                        _state.update { it.copy(isShimmerPopular = isLoading) }
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

                _state.update { it.copy(popular = popular) }
            }
            is HomeEvent.OnSearch -> sendEffect(HomeEffect.OnSearch)
            is HomeEvent.OnFavorite -> onFavorite(product = event.product)
            is HomeEvent.OnProfile -> sendEffect(HomeEffect.OnProfile)
            is HomeEvent.OnDetail -> sendEffect(HomeEffect.OnDetail(productId = event.productId))
            is HomeEvent.OnSeeAllCategory -> sendEffect(HomeEffect.OnSeeAllCategory)
        }
    }

    init {
        fetchFavorite()
    }

    private fun onFavorite(product: Product) = viewModelScope.launch {
        productRepository.insertFavoriteById(
            favorite = Favorite(
                id = product.id,
                isFavorite = !product.isFavorite
            )
        )
        _state.update { currentState ->
            val popular = currentState.popular.map { item ->
                if (item.id == product.id) {
                    item.copy(isFavorite = !product.isFavorite)
                } else {
                    item
                }
            }
            currentState.copy(popular = popular)
        }
    }

    private fun fetchFavorite() = viewModelScope.launch {
        productRepository.getFavorite().collect { favorites ->
            _state.update { currentState ->
                currentState.copy(
                    favoriteId = favorites.map { it.id }.toSet()
                )
            }
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
        handleProduct(response = response)
    }

    private fun handleProduct(response: StoreResponse<List<Product>>) {
        if (response.isError()) {
            sendEffect(HomeEffect.OnGeneralError(message = response.message()))
            return
        }

        val product = response.data().orEmpty()
        val banner = product
            .shuffled(Random(seed))
            .take(LIMIT)

        val categoryMapping = listOf("all") + product
            .map { it.category }
            .distinct()

        val category = categoryMapping.map { it.toCategory() }

        val popular = product
            .shuffled(Random(seed))
            .map {
                it.copy(
                    isFavorite = _state.value.favoriteId.contains(it.id)
                )
            }

        setProduct(product = product)
        setBanner(banner = banner)
        setCategory(category = category)
        setPopularProduct(popular = popular)
    }

    private fun setProduct(product: List<Product>) {
        _state.update {
            it.copy(product = product)
        }
    }

    private fun setBanner(banner: List<Product>) {
        _state.update {
            it.copy(banner = banner)
        }
    }

    private fun setCategory(category: List<Category?>) {
        _state.update {
            it.copy(category = category)
        }
    }

    private fun setPopularProduct(popular: List<Product>) {
        _state.update {
            it.copy(popular = popular)
        }
    }

    private fun sendEffect(effect: HomeEffect) = viewModelScope.launch {
        _effect.send(effect)
    }

    private companion object {
        const val ALL = "all"
        const val LIMIT: Int = 4

        val seed: Int = System.currentTimeMillis().toInt()
    }
}
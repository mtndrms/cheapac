package com.example.cheapac.presentation.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.data.mapper.toCartItem
import com.example.cheapac.data.mapper.toWishlistItem
import com.example.cheapac.domain.model.Product
import com.example.cheapac.domain.use_case.AddProductToCartUseCase
import com.example.cheapac.domain.use_case.AddProductToWishlistUseCase
import com.example.cheapac.domain.use_case.GetAllCategoriesUseCase
import com.example.cheapac.domain.use_case.GetAllProductsUseCase
import com.example.cheapac.domain.use_case.GetCartUseCase
import com.example.cheapac.domain.use_case.GetHighlightsUseCase
import com.example.cheapac.domain.use_case.GetWishlistUseCase
import com.example.cheapac.domain.use_case.RemoveProductFromWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getHighlightsUseCase: GetHighlightsUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val removeProductFromWishlistUseCase: RemoveProductFromWishlistUseCase,
    private val addProductToWishlistUseCase: AddProductToWishlistUseCase,
    private val getWishlistUseCase: GetWishlistUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val getCartUseCase: GetCartUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    private fun getHighlights() {
        job = getHighlightsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(
                            mainHighlights = UiState(isLoading = true),
                            subHighlights = UiState(isLoading = true)
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            mainHighlights = UiState(message = result.message),
                            subHighlights = UiState(message = result.message)
                        )
                    }
                }

                is Resource.Success -> {
                    result.data?.let { data ->
                        _uiState.update {
                            it.copy(
                                mainHighlights = UiState(data = data[0]),
                                subHighlights = UiState(data = data[1])
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllCategories() {
        job = getAllCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(categories = UiState(isLoading = true))
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(categories = UiState(message = result.message))
                    }
                }

                is Resource.Success -> {
                    result.data?.let { data ->
                        _uiState.update {
                            it.copy(categories = UiState(data = data))
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllProducts() {
        job = getAllProductsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(products = UiState(isLoading = true))
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(products = UiState(message = result.message))
                    }
                }

                is Resource.Success -> {
                    result.data?.let { data ->
                        _uiState.update {
                            it.copy(products = UiState(data = data))
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun addProductToWishlist(
        item: Product,
        note: String
    ) {
        val wishlistItem = item.toWishlistItem(note)

        job = addProductToWishlistUseCase(
            product = wishlistItem,
            note = note
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {}
                is Resource.Success -> {
                    val wishlistedProducts = _uiState.value.wishlistedProducts.toMutableList()
                    wishlistedProducts.add(wishlistItem)

                    _uiState.update {
                        it.copy(wishlistedProducts = wishlistedProducts)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun removeProductFromWishlist(id: Int) {
        job = removeProductFromWishlistUseCase(id = id).onEach { result ->
            if (result) {
                val removed = _uiState.value.wishlistedProducts.find { it.id == id }
                _uiState.value.wishlistedProducts.remove(removed)
                _uiState.update {
                    it.copy(wishlistedProducts = it.wishlistedProducts)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getWishlist() {
        job = getWishlistUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(wishlistedProducts = mutableListOf())
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(wishlistedProducts = mutableListOf())
                    }
                }

                is Resource.Success -> {
                    result.data?.let { data ->
                        _uiState.update {
                            it.copy(wishlistedProducts = data.toMutableList())
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun addProductToCart(product: Product) {
        job = addProductToCartUseCase(product).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {}
                is Resource.Success -> {
                    val cart = _uiState.value.cart.toMutableList()
                    cart.add(product.toCartItem())

                    _uiState.update {
                        it.copy(cart = cart)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCart() {
        job = getCartUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {}
                is Resource.Success -> {
                    result.data?.let { data ->
                        _uiState.update { it.copy(cart = data.toMutableList()) }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.InitialFetch -> {
                getHighlights()
                getAllCategories()
                getWishlist()
                getCart()
            }

            is HomeEvent.AddProductToCart -> {
                addProductToCart(product = event.product)
            }

            is HomeEvent.AddProductToWishlist -> {
                addProductToWishlist(
                    item = event.product,
                    note = event.note
                )
            }

            is HomeEvent.RemoveProductFromWishlist -> {
                removeProductFromWishlist(id = event.id)
            }
        }
    }
}

package com.example.cheapac.presentation.feature.search.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.data.mapper.toWishlistItem
import com.example.cheapac.domain.model.Product
import com.example.cheapac.domain.use_case.AddProductToCartUseCase
import com.example.cheapac.domain.use_case.AddProductToWishlistUseCase
import com.example.cheapac.domain.use_case.GetWishlistByCategoryUseCase
import com.example.cheapac.domain.use_case.GetWishlistUseCase
import com.example.cheapac.domain.use_case.RemoveProductFromWishlistUseCase
import com.example.cheapac.domain.use_case.SearchForProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val searchForProductUseCase: SearchForProductUseCase,
    private val getAllWishlistUseCase: GetWishlistUseCase,
    private val addProductToWishlistUseCase: AddProductToWishlistUseCase,
    private val removeProductFromWishlistUseCase: RemoveProductFromWishlistUseCase,
    private val getAddProductToCartUseCase: AddProductToCartUseCase,
) :
    ViewModel() {
    private val _uiState = MutableStateFlow(SearchResultUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllWishlist()
    }

    fun searchForProduct(query: String) {
        searchForProductUseCase(query).map { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(products = UiState(isLoading = true))
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(products = UiState(message = response.message))
                    }
                }

                is Resource.Success -> {
                    response.data?.let { data ->
                        _uiState.update { it.copy(products = UiState(data = data)) }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllWishlist() {
        getAllWishlistUseCase().map { response ->
            when (response) {
                is Resource.Loading,
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(wishlistedProductIDs = mutableListOf())
                    }
                }

                is Resource.Success -> {
                    response.data?.let { data ->
                        _uiState.update { state ->
                            state.copy(wishlistedProductIDs = data.map { it.id }.toMutableList())
                        }
                    }
                }
            }
        }
    }

    fun addToWishlist(
        item: Product,
        note: String
    ) {
        addProductToWishlistUseCase(
            product = item.toWishlistItem(note),
            note = note
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun removeProductFromWishlist(id: Int) {
        removeProductFromWishlistUseCase(id = id).onEach { result ->
            if (result) {
                _uiState.value.wishlistedProductIDs.remove(element = id)
                _uiState.update {
                    it.copy(wishlistedProductIDs = it.wishlistedProductIDs)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addToCart(product: Product) {
        getAddProductToCartUseCase(product).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {}
                is Resource.Success -> {}
            }
        }.launchIn(viewModelScope)
    }
}

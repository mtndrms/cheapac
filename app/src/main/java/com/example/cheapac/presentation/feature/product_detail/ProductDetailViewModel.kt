package com.example.cheapac.presentation.feature.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.domain.model.Product
import com.example.cheapac.domain.use_case.AddProductToWishlistUseCase
import com.example.cheapac.domain.use_case.CheckProductIsWishlistedUseCase
import com.example.cheapac.domain.use_case.GetProductUseCase
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
class ProductDetailViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val addProductToWishlistUseCase: AddProductToWishlistUseCase,
    private val checkProductIsWishlistedUseCase: CheckProductIsWishlistedUseCase,
    private val removeProductFromWishlistUseCase: RemoveProductFromWishlistUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    fun getProduct(id: Int) {
        job = getProductUseCase(id = id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(product = UiState(isLoading = true))
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(product = UiState(message = result.message))
                    }
                }

                is Resource.Success -> {
                    result.data?.let { data ->
                        _uiState.update {
                            it.copy(product = UiState(data = data))
                        }
                        checkIfProductWishlisted(data.id)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addToWishlist(product: Product, note: String) {
        job = addProductToWishlistUseCase(product = product, note).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(addToWishlistStatus = UiState(isLoading = true))
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(addToWishlistStatus = UiState(message = result.message))
                    }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            addToWishlistStatus = UiState(data = result.data),
                            isWishlisted = result.data ?: false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun removeFromWishlist(id: Int) {
        job = removeProductFromWishlistUseCase(id = id).onEach { result ->
            if (result) {
                _uiState.update {
                    it.copy(isWishlisted = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun checkIfProductWishlisted(id: Int) {
        job = checkProductIsWishlistedUseCase(id = id).onEach { result ->
            when (result) {
                is Resource.Loading,
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(isWishlisted = false)
                    }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(isWishlisted = result.data ?: false)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}
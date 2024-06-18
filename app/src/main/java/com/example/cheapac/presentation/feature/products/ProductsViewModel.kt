package com.example.cheapac.presentation.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.domain.use_case.GetProductsOfCategoryUseCase
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.data.mapper.toWishlistItem
import com.example.cheapac.domain.model.Product
import com.example.cheapac.domain.use_case.AddProductToCartUseCase
import com.example.cheapac.domain.use_case.AddProductToWishlistUseCase
import com.example.cheapac.domain.use_case.GetWishlistByCategoryUseCase
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
class ProductsViewModel @Inject constructor(
    private val getProductsOfCategoryUseCase: GetProductsOfCategoryUseCase,
    private val addProductToWishlistUseCase: AddProductToWishlistUseCase,
    private val removeProductFromWishlistUseCase: RemoveProductFromWishlistUseCase,
    private val getWishlistByCategoryUseCase: GetWishlistByCategoryUseCase,
    private val getAddProductToCartUseCase: AddProductToCartUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    private fun getProductsOfCategory(category: String) {
        job = getProductsOfCategoryUseCase(category).onEach { result ->
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
        job = addProductToWishlistUseCase(
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

    private fun removeProductFromWishlist(id: Int) {
        job = removeProductFromWishlistUseCase(id = id).onEach { result ->
            if (result) {
                _uiState.value.wishlistedProductIDs.remove(element = id)
                _uiState.update {
                    it.copy(wishlistedProductIDs = it.wishlistedProductIDs)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getWishlistByCategory(category: String) {
        job = getWishlistByCategoryUseCase(category = category).onEach { result ->
            _uiState.update {
                it.copy(wishlistedProductIDs = result.toMutableList())
            }
        }.launchIn(viewModelScope)
    }

    private fun addProductToCart(product: Product) {
        job = getAddProductToCartUseCase(product).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {}
                is Resource.Success -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ProductsEvent) {
        when (event) {
            is ProductsEvent.InitialFetch -> {
                if (uiState.value.products.data == null) {
                    getProductsOfCategory(category = event.category)
                }

                getWishlistByCategory(category = event.category)
            }

            is ProductsEvent.AddProductToCart -> {
                addProductToCart(product = event.product)
            }

            is ProductsEvent.AddProductToWishlist -> {
                addProductToWishlist(item = event.product, note = event.note)
            }

            is ProductsEvent.RemoveProductFromWishlist -> {
                removeProductFromWishlist(id = event.id)
            }
        }
    }
}

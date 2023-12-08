package com.example.cheapac.presentation.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.domain.use_case.GetProductsOfCategoryUseCase
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.data.local.entity.WishlistItem
import com.example.cheapac.domain.use_case.AddProductToWishlistUseCase
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
    private val addProductToWishlistUseCase: AddProductToWishlistUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    fun getProductsOfCategory(category: String) {
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

    fun addToWishlist(id: Int, title: String, thumbnailUrl: String, note: String) {
        val item = WishlistItem(
            id = id,
            title = title,
            note = note,
            thumbnailUrl = thumbnailUrl
        )

        job = addProductToWishlistUseCase(product = item, note = note).onEach { result ->
            when (result) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}
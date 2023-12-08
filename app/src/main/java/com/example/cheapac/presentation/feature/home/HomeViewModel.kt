package com.example.cheapac.presentation.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.domain.use_case.GetAllCategoriesUseCase
import com.example.cheapac.domain.use_case.GetAllProductsUseCase
import com.example.cheapac.domain.use_case.GetHighlightsUseCase
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.data.local.entity.WishlistItem
import com.example.cheapac.domain.model.Product
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
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getHighlightsUseCase: GetHighlightsUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val addProductToWishlistUseCase: AddProductToWishlistUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        getHighlights()
//        getAllCategories()
//        getAllProducts()
    }

    private fun getHighlights() {
        job = getHighlightsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(highlights = UiState(isLoading = true))
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(highlights = UiState(message = result.message))
                    }
                }

                is Resource.Success -> {
                    result.data?.let { data ->
                        _uiState.update {
                            it.copy(highlights = UiState(data = data))
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

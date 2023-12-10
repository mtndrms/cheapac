package com.example.cheapac.presentation.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.data.local.entity.WishlistItem
import com.example.cheapac.domain.use_case.AddProductToWishlistUseCase
import com.example.cheapac.domain.use_case.GetAllCategoriesUseCase
import com.example.cheapac.domain.use_case.GetAllProductsUseCase
import com.example.cheapac.domain.use_case.GetHighlightsUseCase
import com.example.cheapac.domain.use_case.GetWishlistUseCase
import com.example.cheapac.domain.use_case.RemoveProductFromWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getHighlightsUseCase: GetHighlightsUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val removeProductFromWishlistUseCase: RemoveProductFromWishlistUseCase,
    private val addProductToWishlistUseCase: AddProductToWishlistUseCase,
    private val getWishlistUseCase: GetWishlistUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        getHighlights()
        getAllCategories()
//        getAllProducts()
        getWishlist()
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

    fun addProductToWishlist(
        id: Int,
        title: String,
        thumbnailUrl: String,
        category: String,
        note: String
    ) {
        val item = WishlistItem(
            id = id,
            title = title,
            note = note,
            category = category,
            thumbnailUrl = thumbnailUrl
        )

        job = addProductToWishlistUseCase(product = item, note = note).onEach { result ->
            when (result) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    _uiState.value.wishlistedProducts.add(item)
                    _uiState.update {
                        Log.i(
                            "HomeViewModel",
                            it.wishlistedProducts.first { it.id == item.id }.toString()
                        )
                        it.copy(wishlistedProducts = it.wishlistedProducts)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun removeProductFromWishlist(id: Int) {
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
}

package com.example.cheapac.presentation.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.domain.use_case.GetAllCategoriesUseCase
import com.example.cheapac.domain.use_case.GetAllProductsUseCase
import com.example.cheapac.domain.use_case.GetHighlightsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getHighlightsUseCase: GetHighlightsUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    init {
//        getHighlights()
//        getAllCategories()
//        getAllProducts()
    }

    fun getHighlights() {
        job = viewModelScope.launch {
            try {
                val highlights = getHighlightsUseCase()
                _uiState.update {
                    it.copy(highlights = highlights)
                }
            } catch (exception: Exception) {
                Log.e("HomeViewModel", exception.message ?: "")
            }
        }
    }

    fun getAllCategories() {
        job = viewModelScope.launch {
            try {
                val categories = getAllCategoriesUseCase()
                _uiState.update {
                    it.copy(categories = categories)
                }
            } catch (exception: Exception) {
                Log.e("HomeViewModel", exception.message ?: "")
            }
        }
    }

    fun getAllProducts() {
        job?.cancel()
        job = viewModelScope.launch {
            try {
                val products = getAllProductsUseCase()
                _uiState.update {
                    it.copy(products = products)
                }
            } catch (exception: Exception) {
                Log.e("HomeViewModel", exception.message ?: "")
            }
        }
    }
}
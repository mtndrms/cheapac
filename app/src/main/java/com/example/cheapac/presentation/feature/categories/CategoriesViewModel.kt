package com.example.cheapac.presentation.feature.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.domain.use_case.GetAllCategoriesUseCase
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        getAllCategories()
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
}
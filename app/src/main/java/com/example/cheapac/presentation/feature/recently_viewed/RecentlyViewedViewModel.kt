package com.example.cheapac.presentation.feature.recently_viewed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.domain.use_case.ClearRecentlyViewedItems
import com.example.cheapac.domain.use_case.GetAllRecentlyViewedItems
import com.example.cheapac.domain.use_case.RemoveRecentlyViewedProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecentlyViewedViewModel @Inject constructor(
    private val removeRecentlyViewedProductUseCase: RemoveRecentlyViewedProductUseCase,
    private val getAllRecentlyViewedItems: GetAllRecentlyViewedItems,
    private val clearRecentlyViewedItems: ClearRecentlyViewedItems
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecentlyViewedUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        getAll()
    }

    fun getAll() {
        job = getAllRecentlyViewedItems().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(items = UiState(isLoading = true))
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(items = UiState(message = result.message))
                    }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(items = UiState(data = result.data))
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteById(id: Int) {
        job = removeRecentlyViewedProductUseCase(id = id).onEach { result ->
            if (result) {
            }
        }.launchIn(viewModelScope)
    }

    fun clear() {
        job = clearRecentlyViewedItems().onEach { result ->
            if (result) {
                _uiState.update {
                    it.copy(items = UiState(data = emptyList()))
                }
            }
        }.launchIn(viewModelScope)
    }
}
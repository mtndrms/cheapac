package com.example.cheapac.presentation.feature.recently_viewed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.domain.use_case.ClearRecentlyViewedItemsUseCase
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
    private val clearRecentlyViewedItemsUseCase: ClearRecentlyViewedItemsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecentlyViewedUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    private fun getAll() {
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

    private fun deleteById(id: Int) {
        job = removeRecentlyViewedProductUseCase(id = id).onEach { result ->
            if (result) {
            }
        }.launchIn(viewModelScope)
    }

    private fun clear() {
        job = clearRecentlyViewedItemsUseCase().onEach { result ->
            if (result) {
                _uiState.update {
                    it.copy(items = UiState(data = emptyList()))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: RecentlyViewedEvent) {
        when (event) {
            is RecentlyViewedEvent.InitialFetch -> {
                getAll()
            }

            is RecentlyViewedEvent.Clear -> {
                clear()
            }
        }
    }
}

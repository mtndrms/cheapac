package com.example.cheapac.presentation.feature.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.domain.use_case.ClearWishlistUseCase
import com.example.cheapac.domain.use_case.GetWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val getWishlistUseCase: GetWishlistUseCase,
    private val clearWishlistUseCase: ClearWishlistUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    private fun getWishlist() {
        job = getWishlistUseCase().onEach { result ->
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

    private fun clear() {
        job = clearWishlistUseCase().onEach { result ->
            if (result) {
                _uiState.update {
                    it.copy(items = UiState(data = emptyList()))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: WishlistEvent) {
        when (event) {
            is WishlistEvent.InitialFetch -> {
                getWishlist()
            }

            is WishlistEvent.Clear -> {
                clear()
            }
        }
    }
}

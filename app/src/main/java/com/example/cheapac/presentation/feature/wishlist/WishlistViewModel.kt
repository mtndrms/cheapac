package com.example.cheapac.presentation.feature.wishlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class WishlistViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState = _uiState.asStateFlow()
}
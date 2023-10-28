package com.example.cheapac.presentation.feature.home

import com.example.cheapac.domain.model.Product
import com.example.cheapac.utils.UiState

data class HomeUiState(
    val isSignedIn: Boolean = false,
    val isPremium: Boolean = false,
    val highlights: UiState<List<Product>> = UiState(isLoading = true),
    val categories: UiState<List<String>> = UiState(isLoading = true),
    val products: UiState<List<Product>> = UiState(isLoading = true)
)
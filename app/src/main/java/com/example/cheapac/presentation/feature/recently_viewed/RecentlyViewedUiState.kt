package com.example.cheapac.presentation.feature.recently_viewed

import com.example.cheapac.data.UiState
import com.example.cheapac.domain.model.Product

data class RecentlyViewedUiState(
    val items: UiState<List<Product>> = UiState(isLoading = true)
)
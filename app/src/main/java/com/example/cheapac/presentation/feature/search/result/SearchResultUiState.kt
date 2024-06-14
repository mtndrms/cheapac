package com.example.cheapac.presentation.feature.search.result

import com.example.cheapac.data.UiState
import com.example.cheapac.domain.model.Product

data class SearchResultUiState(
    val products: UiState<List<Product>> = UiState(isLoading = true),
    val wishlistedProductIDs: MutableList<Int> = mutableListOf()
)

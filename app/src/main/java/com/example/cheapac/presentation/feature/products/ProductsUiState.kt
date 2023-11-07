package com.example.cheapac.presentation.feature.products

import com.example.cheapac.domain.model.Product
import com.example.cheapac.data.UiState

data class ProductsUiState(
    val products: UiState<List<Product>> = UiState(isLoading = true)
)

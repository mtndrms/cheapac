package com.example.cheapac.presentation.feature.products

import com.example.cheapac.domain.model.Product
import com.example.cheapac.utils.UiState

data class ProductsUiState(
    val products: UiState<List<Product>> = UiState(isLoading = true)
)

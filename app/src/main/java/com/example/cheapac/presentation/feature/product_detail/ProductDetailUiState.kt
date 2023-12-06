package com.example.cheapac.presentation.feature.product_detail

import com.example.cheapac.domain.model.Product
import com.example.cheapac.data.UiState

data class ProductDetailUiState (
    val addToWishlistStatus: UiState<Boolean> = UiState(isLoading = false),
    val isWishlisted: Boolean = false,
    val product: UiState<Product> = UiState(isLoading = true)
)
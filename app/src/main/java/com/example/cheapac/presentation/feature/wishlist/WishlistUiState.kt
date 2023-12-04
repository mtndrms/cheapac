package com.example.cheapac.presentation.feature.wishlist

import com.example.cheapac.data.UiState
import com.example.cheapac.domain.model.Product

data class WishlistUiState(
    val items: UiState<List<Product>> = UiState(isLoading = true)
)

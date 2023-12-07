package com.example.cheapac.presentation.feature.wishlist

import com.example.cheapac.data.UiState
import com.example.cheapac.data.local.entity.WishlistItem
import com.example.cheapac.domain.model.Product

data class WishlistUiState(
    val items: UiState<List<WishlistItem>> = UiState(isLoading = true)
)

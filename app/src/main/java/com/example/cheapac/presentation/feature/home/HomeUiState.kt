package com.example.cheapac.presentation.feature.home

import com.example.cheapac.domain.model.Category
import com.example.cheapac.domain.model.Product
import com.example.cheapac.data.UiState
import com.example.cheapac.data.local.entity.CartItem
import com.example.cheapac.data.local.entity.WishlistItem

data class HomeUiState(
    val isSignedIn: Boolean = false,
    val isPremium: Boolean = false,
    val mainHighlights: UiState<List<Product>> = UiState(isLoading = true),
    val subHighlights: UiState<List<Product>> = UiState(isLoading = true),
    val categories: UiState<List<Category>> = UiState(isLoading = true),
    val products: UiState<List<Product>> = UiState(isLoading = true),
    val wishlistedProducts: MutableList<WishlistItem> = mutableListOf(),
    val cart: List<CartItem> = mutableListOf()
)

package com.example.cheapac.presentation.feature.cart

import com.example.cheapac.data.UiState
import com.example.cheapac.data.local.entity.CartItem

data class CartUiState(
    val cart: UiState<CartItem> = UiState(isLoading = true)
)

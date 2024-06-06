package com.example.cheapac.presentation.feature.cart

import com.example.cheapac.data.UiState
import com.example.cheapac.data.local.entity.CartItem

data class CartUiState(
    val cart: UiState<List<CartItem>> = UiState(isLoading = true),
) {
    val total: Int
        get() = cart.data?.sumOf { it.price * it.quantity } ?: 0
}

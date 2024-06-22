package com.example.cheapac.presentation.feature.cart

import com.example.cheapac.data.local.entity.CartItem

sealed interface CartEvent {
    data object InitialFetch: CartEvent
    data object Clear: CartEvent
    data class IncrementQuantity(val cartItem: CartItem): CartEvent
    data class DecrementQuantity(val cartItem: CartItem): CartEvent
}

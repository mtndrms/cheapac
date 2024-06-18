package com.example.cheapac.presentation.feature.home

import com.example.cheapac.domain.model.Product

sealed interface HomeEvent {
    data object InitialFetch : HomeEvent
    data class AddProductToWishlist(val product: Product, val note: String) : HomeEvent
    data class RemoveProductFromWishlist(val id: Int) : HomeEvent
    data class AddProductToCart(val product: Product) : HomeEvent
}

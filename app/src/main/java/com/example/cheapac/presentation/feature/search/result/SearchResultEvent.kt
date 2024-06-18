package com.example.cheapac.presentation.feature.search.result

import com.example.cheapac.domain.model.Product

sealed interface SearchResultEvent {
    data class InitialFetch(val query: String) : SearchResultEvent
    data class AddProductToCart(val product: Product) : SearchResultEvent
    data class AddProductToWishlist(val product: Product, val note: String) : SearchResultEvent
    data class RemoveProductFromWishlist(val id: Int) : SearchResultEvent
    data class Search(val query: String) : SearchResultEvent
}

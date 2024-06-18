package com.example.cheapac.presentation.feature.products

import com.example.cheapac.domain.model.Product

sealed interface ProductsEvent {
    data class InitialFetch(val category: String) : ProductsEvent
    data class AddProductToWishlist(val product: Product, val note: String) : ProductsEvent
    data class RemoveProductFromWishlist(val id: Int) : ProductsEvent
    data class AddProductToCart(val product: Product) : ProductsEvent
}

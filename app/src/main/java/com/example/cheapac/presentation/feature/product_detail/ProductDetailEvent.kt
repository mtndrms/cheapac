package com.example.cheapac.presentation.feature.product_detail

import com.example.cheapac.domain.model.Product

sealed interface ProductDetailEvent {
    data class InitialFetch(val id: Int) : ProductDetailEvent
    data class AddProductToWishlist(val product: Product, val note: String) : ProductDetailEvent
    data class RemoveProductFromWishlist(val id: Int) : ProductDetailEvent
}

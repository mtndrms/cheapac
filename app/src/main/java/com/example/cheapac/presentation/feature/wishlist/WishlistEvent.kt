package com.example.cheapac.presentation.feature.wishlist

sealed interface WishlistEvent {
    data object InitialFetch : WishlistEvent
    data object Clear : WishlistEvent
}

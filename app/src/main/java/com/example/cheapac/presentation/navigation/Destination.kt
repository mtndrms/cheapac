package com.example.cheapac.presentation.navigation

import com.example.cheapac.R

enum class Destination(
    val titleTextResId: Int,
    val route: String
) {
    CATEGORIES(
        titleTextResId = R.string.categories,
        route = "categories"
    ),
    PRODUCTS(
        titleTextResId = R.string.products,
        route = "products"
    ),
    PRODUCT_DETAIL(
        titleTextResId = R.string.product_detail,
        route = "product_detail"
    ),
    CART(
        titleTextResId = R.string.your_cart,
        route = "cart"
    ),
    WISHLIST(
        titleTextResId = R.string.wishlist,
        route = "wishlist"
    ),
    PURCHASE_HISTORY(
        titleTextResId = R.string.purchase_history,
        route = "purchase_history"
    ),
    RECENTLY_VIEWED(
        titleTextResId = R.string.recently_viewed,
        route = "recently_viewed"
    )
}
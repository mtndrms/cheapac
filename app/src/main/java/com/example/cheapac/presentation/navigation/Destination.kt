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
    )
}
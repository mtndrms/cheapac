package com.example.cheapac.presentation.feature.wishlist

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.cheapac.presentation.navigation.Destination

fun NavController.navigateToWishlistScreen(navOptions: NavOptions? = null) {
    this.navigate(Destination.WISHLIST.route, navOptions = navOptions)
}

fun NavGraphBuilder.wishlistScreen() {
    composable(route = Destination.WISHLIST.route) {
        WishlistRoute()
    }
}
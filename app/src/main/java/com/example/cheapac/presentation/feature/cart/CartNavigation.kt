package com.example.cheapac.presentation.feature.cart

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.cheapac.presentation.navigation.Destination

fun NavController.navigateToCartScreen(navOptions: NavOptions? = null) {
    this.navigate(Destination.CART.route, navOptions = navOptions)
}

fun NavGraphBuilder.cartScreen(goBack: () -> Unit) {
    composable(route = Destination.CART.route) {
        CartRoute(goBack = goBack)
    }
}
package com.example.cheapac.presentation.feature.products

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cheapac.presentation.navigation.Destination

fun NavController.navigateToProductList(category: String, navOptions: NavOptions? = null) {
    this.navigate("${Destination.PRODUCTS.route}/$category", navOptions)
}

fun NavGraphBuilder.productsScreen() {
    composable(
        route = "${Destination.PRODUCTS.route}/{category}",
        arguments = listOf(navArgument("category") { type = NavType.StringType })
    ) { backStackEntry ->
        val category = backStackEntry.arguments?.getString("category")
        ProductsRoute(category = category)
    }
}
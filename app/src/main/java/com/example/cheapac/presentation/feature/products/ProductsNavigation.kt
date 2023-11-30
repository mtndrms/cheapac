package com.example.cheapac.presentation.feature.products

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cheapac.presentation.navigation.Destination
import okio.internal.commonToUtf8String
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun NavController.navigateToProductList(
    category: String,
    title: String,
    navOptions: NavOptions? = null
) {
    this.navigate(
        "${Destination.PRODUCTS.route}/$category/${
            Base64.UrlSafe.encode(title.toByteArray())
        }", navOptions
    )
}

@OptIn(ExperimentalEncodingApi::class)
fun NavGraphBuilder.productsScreen(navigateToProductDetail: (Int) -> Unit, goBack: () -> Unit) {
    composable(
        route = "${Destination.PRODUCTS.route}/{category}/{title}",
        arguments = listOf(navArgument("category") { type = NavType.StringType })
    ) { backStackEntry ->
        val category = backStackEntry.arguments?.getString("category")
        val title = Base64.UrlSafe.decode(backStackEntry.arguments?.getString("title") ?: "")
        ProductsRoute(
            category = category,
            title = title.commonToUtf8String(),
            navigateToProductDetail = navigateToProductDetail,
            goBack = goBack
        )
    }
}
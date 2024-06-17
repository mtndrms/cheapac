package com.example.cheapac.presentation.feature.product_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cheapac.data.remote.dto.product.Review
import com.example.cheapac.presentation.navigation.Destination

fun NavController.navigateToProductDetail(id: Int, navOptions: NavOptions? = null) {
    this.navigate(route = "${Destination.PRODUCT_DETAIL.route}/$id", navOptions = navOptions)
}

fun NavGraphBuilder.productDetailScreen(
    navigateToProductList: (String, String) -> Unit,
    navigateToSearchResultScreen: (String) -> Unit,
    navigateToReviewsScreen: (List<Review>) -> Unit,
    goBack: () -> Unit
) {
    composable(
        route = "${Destination.PRODUCT_DETAIL.route}/{id}",
        arguments = listOf(navArgument(name = "id") { type = NavType.IntType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getInt("id")
        ProductDetailRoute(
            id = id,
            navigateToProductList = navigateToProductList,
            navigateToSearchResultScreen = navigateToSearchResultScreen,
            navigateToReviewsScreen = navigateToReviewsScreen,
            goBack = goBack
        )
    }
}
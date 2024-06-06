package com.example.cheapac.presentation.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.cheapac.presentation.navigation.TopLevelDestination

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(TopLevelDestination.HOME.route, navOptions)
}

fun NavGraphBuilder.homeScreen(
    navigateToCategories: () -> Unit,
    navigateToCategory: (code: String, title: String) -> Unit,
    navigateToProductDetail: (Int) -> Unit,
    navigateToProfile: (TopLevelDestination) -> Unit,
    navigateToCartScreen: () -> Unit,
) {
    composable(route = TopLevelDestination.HOME.route) {
        HomeRoute(
            navigateToCategories = navigateToCategories,
            navigateToCategory = navigateToCategory,
            navigateToProductDetail = navigateToProductDetail,
            navigateToProfile = navigateToProfile,
            navigateToCartScreen = navigateToCartScreen,
        )
    }
}
package com.example.cheapac.presentation.feature.categories

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.cheapac.presentation.navigation.Destination

fun NavController.navigateToCategories(navOptions: NavOptions? = null) {
    this.navigate(Destination.CATEGORIES.route, navOptions)
}

fun NavGraphBuilder.categoriesScreen(
    goBack: () -> Unit,
    navigateToCategory: (code: String, title: String) -> Unit
) {
    composable(Destination.CATEGORIES.route) {
        CategoriesRoute(goBack = goBack, navigateToCategory = navigateToCategory)
    }
}
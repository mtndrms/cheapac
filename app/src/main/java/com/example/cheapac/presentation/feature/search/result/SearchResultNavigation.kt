package com.example.cheapac.presentation.feature.search.result

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cheapac.presentation.navigation.Destination

fun NavController.navigateToSearchResultScreen(query: String, navOptions: NavOptions? = null) {
    this.navigate("${Destination.SEARCH.route}/$query", navOptions = navOptions)
}

fun NavGraphBuilder.searchResultScreen(navigateToProductDetail: (Int) -> Unit, goBack: () -> Unit) {
    composable(
        route = "${Destination.SEARCH.route}/{query}",
        arguments = listOf(navArgument(name = "query") { type = NavType.StringType })
    ) { backStackEntry ->
        val query = backStackEntry.arguments?.getString("query")
        SearchResultRoute(
            query = query,
            navigateToProductDetail = navigateToProductDetail,
            goBack = goBack
        )
    }
}

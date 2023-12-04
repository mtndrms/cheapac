package com.example.cheapac.presentation.feature.recently_viewed

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.cheapac.presentation.navigation.Destination

fun NavController.navigateToRecentlyViewedScreen(navOptions: NavOptions? = null) {
    this.navigate(Destination.RECENTLY_VIEWED.route, navOptions = navOptions)
}

fun NavGraphBuilder.recentlyViewedScreen() {
    composable(route = Destination.RECENTLY_VIEWED.route) {
        RecentlyViewedRoute()
    }
}
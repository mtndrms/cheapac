package com.example.cheapac.presentation.feature.profile

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.cheapac.presentation.navigation.TopLevelDestination

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(TopLevelDestination.PROFILE.route, navOptions)
}

fun NavGraphBuilder.profileScreen(goBack: () -> Unit) {
    composable(
        route = TopLevelDestination.PROFILE.route,
        enterTransition = {
            slideInVertically(
                initialOffsetY = {
                    it / 3
                }
            )
        },
        exitTransition = {
            slideOutVertically(
                targetOffsetY = {
                    it
                }
            )
        }
    ) {
        ProfileRoute(goBack)
    }
}
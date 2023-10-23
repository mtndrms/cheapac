package com.example.cheapac.presentation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.core.os.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.cheapac.presentation.navigation.TopLevelDestination
import com.example.cheapac.presentation.feature.home.navigateToHome
import com.example.cheapac.utils.getDestinationTitle
import com.example.cheapac.utils.hideBottomBarOnThisDestinations
import com.example.cheapac.utils.showTopBarOnThisDestinations
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    windowSizeClass: WindowSizeClass
): AppState {
    return remember(navController) {
        AppState(navController = navController, coroutineScope, windowSizeClass)
    }
}

class AppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentDestinationTitle: String
        @Composable get() = stringResource(id = currentDestination.getDestinationTitle())

    val shouldShowTopBar: Boolean
        @Composable get() = currentDestination.showTopBarOnThisDestinations()

    val shouldShowBottomBar: Boolean
        @Composable get() = !currentDestination.hideBottomBarOnThisDestinations()

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            val topLevelDestinationNavOptions: NavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // re-selecting the same item
                launchSingleTop = true
                // Restore state when re-selecting a previously selected item
                // FIXME: This breaks the bottom bar
                // restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHome(topLevelDestinationNavOptions)
            }
        }
    }
}

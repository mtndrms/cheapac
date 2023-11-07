package com.example.cheapac.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.cheapac.presentation.common.TopBar
import com.example.cheapac.presentation.feature.categories.categoriesScreen
import com.example.cheapac.presentation.feature.categories.navigateToCategories
import com.example.cheapac.presentation.navigation.TopLevelDestination
import com.example.cheapac.presentation.feature.home.homeScreen
import com.example.cheapac.presentation.feature.product_detail.navigateToProductDetail
import com.example.cheapac.presentation.feature.product_detail.productDetailScreen
import com.example.cheapac.presentation.feature.products.navigateToProductList
import com.example.cheapac.presentation.feature.products.productsScreen
import com.example.cheapac.presentation.feature.profile.profileScreen

@Composable
fun AppContainer(
    windowSizeClass: WindowSizeClass,
    appState: AppState = rememberAppState(windowSizeClass = windowSizeClass)
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                if (appState.shouldShowTopBar) {
                    TopBar(
                        currentScreenTitle = appState.currentDestinationTitle,
                        navigateToProfile = {
                            appState.navigateToTopLevelDestination(
                                TopLevelDestination.PROFILE
                            )
                        },
                        onTitleClick = { appState.navigateToTopLevelDestination(TopLevelDestination.HOME) }
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                navController = appState.navController,
                startDestination = TopLevelDestination.HOME.route
            ) {
                homeScreen(
                    navigateToCategories = appState.navController::navigateToCategories,
                    navigateToCategory = appState.navController::navigateToProductList,
                    navigateToProductDetail = appState.navController::navigateToProductDetail
                )
                categoriesScreen()
                profileScreen(goBack = appState.navController::popBackStack)
                productsScreen(navigateToProductDetail = appState.navController::navigateToProductDetail)
                productDetailScreen(goBack = appState.navController::popBackStack)
            }
        }
    }
}

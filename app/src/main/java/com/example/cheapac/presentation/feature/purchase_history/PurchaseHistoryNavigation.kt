package com.example.cheapac.presentation.feature.purchase_history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.cheapac.presentation.navigation.Destination

fun NavController.navigateToPurchaseHistoryScreen(navOptions: NavOptions? = null) {
    this.navigate(Destination.PURCHASE_HISTORY.route, navOptions = navOptions)
}

fun NavGraphBuilder.purchaseHistoryScreen() {
    composable(route = Destination.PURCHASE_HISTORY.route) {
        PurchaseHistoryRoute()
    }
}
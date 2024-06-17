package com.example.cheapac.presentation.feature.review

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.cheapac.data.remote.dto.product.Review
import com.example.cheapac.presentation.navigation.Destination
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun NavController.navigateToReviewsScreen(reviews: List<Review>, navOptions: NavOptions? = null) {
    val json = Json.encodeToString(reviews)
    this.navigate(route = "${Destination.REVIEW.route}/$json", navOptions = navOptions)
}

fun NavGraphBuilder.reviewsScreen(goBack: () -> Unit) {
    composable(route = "${Destination.REVIEW.route}/{reviews}") { backStackEntry ->
        val json = backStackEntry.arguments?.getString("reviews")
        val reviews = json?.let { jsonString ->
            Json.decodeFromString<List<Review>>(jsonString)
        }

        ReviewRoute(goBack = goBack, reviews = reviews.orEmpty())
    }
}

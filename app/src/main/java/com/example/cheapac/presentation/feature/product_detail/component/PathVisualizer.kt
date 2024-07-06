package com.example.cheapac.presentation.feature.product_detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cheapac.data.mapper.betterCategoryTitle

/**
 * A Composable function that visualizes a path consisting of a category and a brand.
 *
 * Example
 * ```
 * Category > Brand
 * ```
 *
 * @param category The category to display in the path visualizer.
 * @param brand The brand to display in the path visualizer.
 * @param navigateToProductList Callback function to navigate to a product list when the category name clicked.
 * @param navigateToSearchResultScreen Callback function to navigate to a search result screen when the brand clicked.
 */
@Composable
fun PathVisualizer(
    category: String,
    brand: String,
    navigateToProductList: (String, String) -> Unit,
    navigateToSearchResultScreen: (String) -> Unit,
) {
    Row {
        Text(
            text = betterCategoryTitle(category),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.clickable {
                navigateToProductList(
                    category,
                    betterCategoryTitle(category)
                )
            })
        Text(
            ">",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        Text(
            text = brand,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.clickable { navigateToSearchResultScreen(brand) })
    }
}

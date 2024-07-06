package com.example.cheapac.presentation.feature.product_detail.component

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cheapac.R
import com.example.cheapac.data.UiState
import com.example.cheapac.data.remote.dto.product.Review
import com.example.cheapac.domain.model.Product
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.RatingBar
import com.example.cheapac.utils.capitalize
import com.example.cheapac.utils.handleShareProductClick

@Composable
fun Description(
    context: Context,
    scrollState: ScrollState,
    product: Product,
    addToWishlistStatus: UiState<Boolean>,
    isWishlisted: Boolean,
    modifier: Modifier,
    addProductToWishlist: (Product, String) -> Unit,
    removeProductFromWishlist: (Int) -> Unit,
    navigateToProductList: (String, String) -> Unit,
    navigateToSearchResultScreen: (String) -> Unit,
    navigateToReviewsScreen: (List<Review>) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .then(modifier)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PathVisualizer(
                    category = product.category,
                    brand = product.brand,
                    navigateToProductList = navigateToProductList,
                    navigateToSearchResultScreen = navigateToSearchResultScreen,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (addToWishlistStatus.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        addToWishlistStatus.message?.let {
                            Icon(
                                imageVector = CheapacIcons.Error,
                                tint = MaterialTheme.colorScheme.error,
                                contentDescription = "an error occurt while adding product to the wishlist"
                            )
                        } ?: run {
                            if (!isWishlisted) {
                                IconButton(onClick = {
                                    addProductToWishlist(
                                        product,
                                        "test"
                                    )
                                }) {
                                    Icon(
                                        imageVector = CheapacIcons.FavoriteOutlined,
                                        contentDescription = "favorite"
                                    )
                                }
                            } else {
                                IconButton(onClick = { removeProductFromWishlist(product.id) }) {
                                    Icon(
                                        imageVector = CheapacIcons.Favorite,
                                        contentDescription = "unfavorite"
                                    )
                                }
                            }
                        }
                    }

                    IconButton(
                        onClick = {
                            handleShareProductClick(context = context, title = product.title)
                        }
                    ) {
                        Icon(
                            imageVector = CheapacIcons.Share,
                            contentDescription = "share"
                        )
                    }
                }
            }
            Text(text = product.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navigateToReviewsScreen(product.reviews)
                    },
            ) {
                RatingBar(rating = product.reviews.sumOf { it.rating } / product.reviews.size)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(
                        R.string.product_detail_review_label,
                        product.reviews.sumOf { it.rating } / product.reviews.size,
                        product.reviews.size
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = product.description.capitalize().repeat(10))
        }
    }
}

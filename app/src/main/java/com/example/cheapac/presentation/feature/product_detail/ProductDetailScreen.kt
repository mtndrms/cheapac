package com.example.cheapac.presentation.feature.product_detail

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.data.remote.dto.product.Review
import com.example.cheapac.presentation.feature.product_detail.component.BottomBarProductDetail
import com.example.cheapac.presentation.feature.product_detail.component.CollapsingToolbar
import com.example.cheapac.presentation.feature.product_detail.component.Description

@Composable
internal fun ProductDetailRoute(
    id: Int?,
    goBack: () -> Unit,
    navigateToProductList: (String, String) -> Unit,
    navigateToSearchResultScreen: (String) -> Unit,
    navigateToReviewsScreen: (List<Review>) -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductDetailScreen(
        id = id,
        uiState = uiState,
        onEvent = viewModel::onEvent,
        navigateToProductList = navigateToProductList,
        navigateToSearchResultScreen = navigateToSearchResultScreen,
        navigateToReviewsScreen = navigateToReviewsScreen,
        goBack = goBack
    )
}

@Composable
private fun ProductDetailScreen(
    id: Int?,
    uiState: ProductDetailUiState,
    goBack: () -> Unit,
    onEvent: (ProductDetailEvent) -> Unit,
    navigateToProductList: (String, String) -> Unit,
    navigateToSearchResultScreen: (String) -> Unit,
    navigateToReviewsScreen: (List<Review>) -> Unit,
) {
    val scrollState = rememberScrollState()
    var topBoxSize by remember { mutableStateOf(IntSize(1, 1)) }
    val topBoxHeight = topBoxSize.height
    var calculatedWeight by remember { mutableFloatStateOf(1f) }
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    LaunchedEffect(key1 = scrollState.value) {
        calculatedWeight =
            (topBoxHeight.toFloat() / (topBoxHeight + scrollState.value)).coerceIn(0.001f, 1f)
    }

    id?.let {
        LaunchedEffect(true) {
            onEvent(ProductDetailEvent.InitialFetch(id = it))
        }
    }

    uiState.product.data?.let { data ->
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(weight = 1f, fill = true)) {
                Column(modifier = Modifier.fillMaxSize()) {
                    CollapsingToolbar(
                        product = data,
                        goBack = goBack,
                        visibilityState = state,
                        modifier = Modifier
                            .weight(calculatedWeight)
                            .onGloballyPositioned { layoutCoordinates: LayoutCoordinates ->
                                topBoxSize = layoutCoordinates.size
                            }
                    )

                    Description(
                        context = LocalContext.current,
                        scrollState = scrollState,
                        addProductToWishlist = { product, note ->
                            onEvent(
                                ProductDetailEvent.AddProductToWishlist(
                                    product = product,
                                    note = note
                                )
                            )
                        },
                        removeProductFromWishlist = { id ->
                            onEvent(ProductDetailEvent.RemoveProductFromWishlist(id = id))
                        },
                        addToWishlistStatus = uiState.addToWishlistStatus,
                        navigateToProductList = navigateToProductList,
                        navigateToSearchResultScreen = navigateToSearchResultScreen,
                        navigateToReviewsScreen = navigateToReviewsScreen,
                        isWishlisted = uiState.isWishlisted,
                        product = data,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            BottomBarProductDetail(
                price = data.price,
                discountRate = data.discountPercentage.toInt(),
                isInStock = data.stock != 0
            )
        }
    }

    if (uiState.product.isLoading) {
        LoadingState()
    }

    uiState.product.message?.let { message ->
        ErrorState(message = message)
    }
}

@Composable
private fun LoadingState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(message: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}

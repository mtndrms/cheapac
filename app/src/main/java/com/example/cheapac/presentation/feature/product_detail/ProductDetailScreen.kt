package com.example.cheapac.presentation.feature.product_detail

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.cheapac.R
import com.example.cheapac.data.UiState
import com.example.cheapac.data.mapper.betterCategoryTitle
import com.example.cheapac.data.remote.dto.product.Review
import com.example.cheapac.domain.model.Product
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.RatingBar
import com.example.cheapac.utils.applyDiscount
import com.example.cheapac.utils.capitalize
import com.example.cheapac.utils.handleShareProductClick

private const val BOTTOM_BAR_HEIGHT = 96

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

@Composable
private fun CollapsingToolbar(
    product: Product,
    goBack: () -> Unit,
    visibilityState: MutableTransitionState<Boolean>,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        IconButton(
            onClick = goBack,
            modifier = Modifier
                .zIndex(1f)
                .padding(10.dp)
                .align(Alignment.TopStart)
                .alpha(0.8f)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .size(36.dp)
        ) {
            Icon(
                imageVector = CheapacIcons.ArrowBack,
                contentDescription = "go back",
                tint = Color.White
            )
        }

        AnimatedVisibility(
            visibleState = visibilityState,
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier
                .zIndex(1f)
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (product.stock != 0) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )
                .padding(vertical = 5.dp, horizontal = 10.dp)
        ) {
            Text(
                text = if (product.stock != 0) {
                    stringResource(R.string.x_in_stock, product.stock)
                } else {
                    stringResource(id = R.string.out_of_stock)
                },
                color = if (product.stock != 0) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onError
                }
            )
        }

        SubcomposeAsyncImage(
            model = product.thumbnail,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            loading = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun Description(
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

@Composable
private fun PathVisualizer(
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

@Composable
private fun BottomBarProductDetail(
    price: Int,
    discountRate: Int,
    isInStock: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(BOTTOM_BAR_HEIGHT.dp)
            .background(MaterialTheme.colorScheme.surface)
            .then(modifier)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                Column {
                    if (discountRate > 1) {
                        Row {
                            Text(
                                text = "$${price}",
                                color = MaterialTheme.colorScheme.onSurface,
                                textDecoration = TextDecoration.LineThrough,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "$discountRate%",
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Icon(
                                imageVector = CheapacIcons.ArrowDown,
                                contentDescription = "discount icon",
                                tint = Color.Green
                            )
                        }
                    }

                    Text(
                        text = "$${price.applyDiscount(discountRate)}",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                if (isInStock) {
                    Button(onClick = { }) {
                        Icon(
                            imageVector = CheapacIcons.CartOutlined,
                            contentDescription = stringResource(
                                id = R.string.add_to_cart
                            ).lowercase()
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = stringResource(id = R.string.add_to_cart),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                } else {
                    Button(onClick = { }) {
                        Text(text = stringResource(R.string.notify_me))
                    }
                }
            }
        }
    }
}

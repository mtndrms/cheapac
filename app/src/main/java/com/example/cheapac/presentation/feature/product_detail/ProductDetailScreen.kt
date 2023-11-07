package com.example.cheapac.presentation.feature.product_detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.cheapac.R
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.RatingBar
import com.example.cheapac.utils.applyDiscount
import com.example.cheapac.utils.capitalize

private val bottomSectionHeight = 96.dp

@Composable
internal fun ProductDetailRoute(
    id: Int?,
    goBack: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProductDetailScreen(
        id = id,
        uiState = uiState,
        getProduct = viewModel::getProduct,
        goBack = goBack
    )
}

@Composable
internal fun ProductDetailScreen(
    id: Int?,
    getProduct: (Int) -> Unit,
    goBack: () -> Unit,
    uiState: ProductDetailUiState
) {
    var offset by remember { mutableStateOf(0f) }

    val scrollState = rememberScrollState()

    val isReachedToTop = remember { derivedStateOf { scrollState.value == 0 } }
    val isReachedToBottom = remember { derivedStateOf { scrollState.value == scrollState.maxValue } }

    val scrollableState = rememberScrollableState { delta ->
        offset += delta
        delta
    }

    id?.let {
        LaunchedEffect(true) {
            getProduct(it)
        }
    }

    if (isReachedToTop.value) {
        Log.i("ProductDetailScreen", "TOP")
    }

    if (isReachedToBottom.value) {
        Log.i("ProductDetailScreen", "BOTTOM")
    }

    uiState.product.data?.let { data ->
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(weight = 1f, fill = true)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .align(Alignment.TopCenter)
                ) {
                    IconButton(
                        onClick = goBack,
                        modifier = Modifier
                            .zIndex(1f)
                            .padding(10.dp)
                            .align(Alignment.TopStart)
                            .alpha(0.2f)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.tertiary)
                            .size(36.dp)
                    ) {
                        Icon(
                            imageVector = CheapacIcons.ArrowBack,
                            contentDescription = "go back",
                            tint = Color.White
                        )
                    }

                    SubcomposeAsyncImage(
                        model = data.thumbnail,
                        contentDescription = data.title,
                        contentScale = ContentScale.Crop,
                        loading = { CircularProgressIndicator() },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .align(Alignment.BottomCenter)
                        .scrollable(
                            orientation = Orientation.Vertical,
                            state = scrollableState
                        )
                        .verticalScroll(scrollState)
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
                            Text(text = data.title, style = MaterialTheme.typography.titleLarge)
                            Row {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = CheapacIcons.FavoriteOutlined,
                                        contentDescription = "favorite"
                                    )
                                }

                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = CheapacIcons.Share,
                                        contentDescription = "share"
                                    )
                                }
                            }
                        }
                        RatingBar(rating = data.rating)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = data.description.capitalize().repeat(20))
                    }
                }
            }

            BottomSectionProductDetail(
                price = data.price,
                discountRate = data.discountPercentage.toInt()
            )
        }
    }

    if (uiState.product.isLoading) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }

    uiState.product.message?.let { message ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(text = message, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun BottomSectionProductDetail(price: Int, discountRate: Int, modifier: Modifier = Modifier) {
    Surface(
        shadowElevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(bottomSectionHeight)
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
                        }
                    }
                    Text(
                        text = "$${price.applyDiscount(discountRate)}",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.add_to_cart))
                }
            }
        }
    }
}

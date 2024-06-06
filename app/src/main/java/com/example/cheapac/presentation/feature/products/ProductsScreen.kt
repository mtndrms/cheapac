package com.example.cheapac.presentation.feature.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.domain.model.Product
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.ProductCard
import com.example.cheapac.utils.capitalize

@Composable
internal fun ProductsRoute(
    category: String?,
    title: String,
    goBack: () -> Unit,
    navigateToProductDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductsScreen(
        modifier = modifier,
        uiState = uiState,
        goBack = goBack,
        navigateToProductDetail = navigateToProductDetail,
        addToWishlist = viewModel::addToWishlist,
        removeProductFromWishlist = viewModel::removeProductFromWishlist,
        addToCart = viewModel::addToCart,
        getWishlistByCategory = viewModel::getWishlistByCategory,
        getProductsOfCategory = viewModel::getProductsOfCategory,
        category = category,
        title = title
    )
}

@Composable
private fun ProductsScreen(
    modifier: Modifier,
    uiState: ProductsUiState,
    goBack: () -> Unit,
    navigateToProductDetail: (Int) -> Unit,
    addToWishlist: (Product, String) -> Unit,
    removeProductFromWishlist: (Int) -> Unit,
    addToCart: (Product) -> Unit,
    getWishlistByCategory: (String) -> Unit,
    getProductsOfCategory: (String) -> Unit,
    category: String?,
    title: String
) {
    category?.let {
        LaunchedEffect(true) {
            if (uiState.products.data == null) {
                getProductsOfCategory(it)
            }

            getWishlistByCategory(it)
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        Header(title = title, goBack = goBack)
        uiState.products.data?.let { data ->
            SuccesState(
                products = data,
                wishlistedProductIDs = uiState.wishlistedProductIDs,
                navigateToProductDetail = navigateToProductDetail,
                addToWishlist = addToWishlist,
                removeProductFromWishlist = removeProductFromWishlist,
                addToCart = addToCart,
            )
        }

        if (uiState.products.isLoading) {
            LoadingState(modifier = modifier)
        }

        uiState.products.message?.let { message ->
            ErrorState(message = message, modifier = modifier)
        }
    }
}

@Composable
private fun SuccesState(
    products: List<Product>,
    wishlistedProductIDs: List<Int>,
    navigateToProductDetail: (Int) -> Unit,
    addToWishlist: (Product, String) -> Unit,
    removeProductFromWishlist: (Int) -> Unit,
    addToCart: (Product) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(products) { product: Product ->
            val isInWishlist = wishlistedProductIDs.contains(product.id)

            ProductCard(
                product = product,
                discountRate = product.discountPercentage.toInt(),
                isInStock = product.stock != 0,
                isWishlisted = isInWishlist,
                navigateToProductDetail = navigateToProductDetail,
                addToWishlist = addToWishlist,
                removeProductFromWishlist = removeProductFromWishlist,
                addToCart = addToCart,
            )
        }
    }
}

@Composable
private fun LoadingState(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(message: String, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
private fun Header(title: String, goBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = goBack) {
                Icon(
                    imageVector = CheapacIcons.ArrowBack,
                    contentDescription = "go back",
                    modifier = Modifier.size(32.dp)
                )
            }

            Text(
                text = title.capitalize(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.size(48.dp))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.15f)
        )
    }
}

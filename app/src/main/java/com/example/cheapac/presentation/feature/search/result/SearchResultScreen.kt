package com.example.cheapac.presentation.feature.search.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import com.example.cheapac.presentation.component.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.domain.model.Product
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.NothingToListState
import com.example.cheapac.presentation.component.ProductCard
import com.example.cheapac.presentation.navigation.Destination
import com.example.cheapac.utils.capitalize

@Composable
fun SearchResultRoute(
    query: String?,
    goBack: () -> Unit,
    navigateToProductDetail: (Int) -> Unit,
    viewModel: SearchResultViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchResultScreen(
        query = query,
        search = viewModel::searchForProduct,
        uiState = uiState,
        navigateToProductDetail = navigateToProductDetail,
        addToCart = viewModel::addToCart,
        addToWishlist = viewModel::addToWishlist,
        removeProductFromWishlist = viewModel::removeProductFromWishlist,
        goBack = goBack
    )
}

@Composable
private fun SearchResultScreen(
    query: String?,
    search: (String) -> Unit,
    uiState: SearchResultUiState,
    navigateToProductDetail: (Int) -> Unit,
    addToWishlist: (Product, String) -> Unit,
    removeProductFromWishlist: (Int) -> Unit,
    addToCart: (Product) -> Unit,
    goBack: () -> Unit,
) {
    query?.let {
        LaunchedEffect(key1 = true) {
            search(it.lowercase())
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Header(title = stringResource(id = Destination.SEARCH.titleTextResId), goBack = goBack)
        SearchBar(
            query = query ?: "",
            onSearchClick = search,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        uiState.products.data?.let { products ->
            SuccessState(
                products = products,
                wishlistedProductIDs = uiState.wishlistedProductIDs,
                navigateToProductDetail = navigateToProductDetail,
                addToWishlist = addToWishlist,
                removeProductFromWishlist = removeProductFromWishlist,
                addToCart = addToCart,
            )
        }

        if (uiState.products.isLoading) {
            LoadingState()
        }

        uiState.products.message?.let { message ->
            ErrorState(message = message)
        }
    }
}

@Composable
private fun SuccessState(
    products: List<Product>,
    wishlistedProductIDs: List<Int>,
    navigateToProductDetail: (Int) -> Unit,
    addToWishlist: (Product, String) -> Unit,
    removeProductFromWishlist: (Int) -> Unit,
    addToCart: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    if (products.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier
                .fillMaxSize()
                .then(modifier)
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
    } else {
        NothingToListState(
            label = "No results for your search.\nTry search something else.",
            imageVector = CheapacIcons.Search
        )
    }
}

@Composable
private fun ErrorState(message: String, modifier: Modifier = Modifier) {
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
private fun LoadingState(modifier: Modifier = Modifier) {
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
    }
}

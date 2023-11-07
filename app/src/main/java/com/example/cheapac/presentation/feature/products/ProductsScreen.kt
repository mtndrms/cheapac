package com.example.cheapac.presentation.feature.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.presentation.component.ProductCard
import com.example.cheapac.utils.capitalize

@Composable
fun ProductsRoute(
    category: String?,
    navigateToProductDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProductsScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToProductDetail = navigateToProductDetail,
        fetchData = viewModel::getProductsOfCategory,
        category = category
    )
}

@Composable
fun ProductsScreen(
    modifier: Modifier,
    uiState: ProductsUiState,
    navigateToProductDetail: (Int) -> Unit,
    fetchData: (String) -> Unit,
    category: String?
) {
    category?.let {
        LaunchedEffect(true) {
            if (uiState.products.data == null) {
                fetchData(it)
            }
        }
    }

    uiState.products.data?.let { data ->
        Column(modifier = Modifier.fillMaxSize()) {
            Header(title = category ?: "null")
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(data) { product ->
                    ProductCard(
                        id = product.id,
                        title = product.title,
                        price = product.price,
                        imageUrl = product.thumbnail,
                        navigateToProductDetail = navigateToProductDetail
                    )
                }
            }
        }
    }

    if (uiState.products.isLoading) {
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

    uiState.products.message?.let { message ->
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
}

@Composable
fun Header(title: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = title.capitalize(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Divider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.15f)
        )
    }
}

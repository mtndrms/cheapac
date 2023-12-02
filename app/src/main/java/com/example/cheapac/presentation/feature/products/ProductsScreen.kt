package com.example.cheapac.presentation.feature.products

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.domain.model.Product
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.ProductCard
import com.example.cheapac.utils.capitalize

@Composable
fun ProductsRoute(
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
        fetchData = viewModel::getProductsOfCategory,
        category = category,
        title = title
    )
}

@Composable
fun ProductsScreen(
    modifier: Modifier,
    uiState: ProductsUiState,
    goBack: () -> Unit,
    navigateToProductDetail: (Int) -> Unit,
    fetchData: (String) -> Unit,
    category: String?,
    title: String
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
            Header(title = title, goBack = goBack)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(data) { product: Product ->
                    ProductCard(
                        id = product.id,
                        title = product.title,
                        price = product.price,
                        imageUrl = product.thumbnail,
                        discountRate = product.discountPercentage.toInt(),
                        isInStock = product.stock != 0,
                        navigateToProductDetail = navigateToProductDetail
                    )
                }
            }
        }
    }

    if (uiState.products.isLoading) {
       LoadingState(modifier = modifier)
    }

    uiState.products.message?.let { message ->
        ErrorState(message = message, modifier = modifier)
    }
}

@Composable
fun LoadingState(modifier: Modifier) {
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
fun ErrorState(message: String, modifier: Modifier) {
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
fun Header(title: String, goBack: () -> Unit) {
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

package com.example.cheapac.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cheapac.R
import com.example.cheapac.domain.model.Product
import com.example.cheapac.data.UiState

@Composable
fun HorizontalProducts(
    products: UiState<List<Product>>,
    navigateToProductDetail: (Int) -> Unit,
    modifier: Modifier
) {
    Text(
        text = stringResource(R.string.featured),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
    )

    Spacer(modifier = Modifier.height(10.dp))

    products.data?.let { data ->
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier)
        ) {
            itemsIndexed(data) { index, product ->
                if (index == 0) {
                    Spacer(modifier = Modifier.width(20.dp))
                }

                ProductCard(
                    id = product.id,
                    title = product.title,
                    price = product.price,
                    imageUrl = product.thumbnail,
                    discountRate = product.discountPercentage.toInt(),
                    isInStock = product.stock != 0,
                    navigateToProductDetail = navigateToProductDetail
                )

                if (index == data.size - 1) {
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        }
    }

    if (products.isLoading) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier)
        ) {
            CircularProgressIndicator()
        }
    }

    products.message?.let { message ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier)
        ) {
            Text(text = message, color = MaterialTheme.colorScheme.error)
        }
    }
}
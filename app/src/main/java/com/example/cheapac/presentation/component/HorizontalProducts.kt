package com.example.cheapac.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cheapac.domain.model.Product

@Composable
fun HorizontalProducts(products: List<Product>, modifier: Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        items((1..10).toList()) { product ->
            if (product == 1) { Spacer(modifier = Modifier.width(20.dp)) }
            ProductCard(title = "Product title", price = product, imageUrl = "")
            if (product == 10) { Spacer(modifier = Modifier.width(20.dp)) }
        }
    }
}
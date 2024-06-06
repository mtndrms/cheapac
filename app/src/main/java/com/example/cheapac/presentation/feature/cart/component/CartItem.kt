package com.example.cheapac.presentation.feature.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.cheapac.data.local.entity.CartItem
import com.example.cheapac.presentation.common.CheapacIcons

@Composable
fun CartItem(item: CartItem, incrementQuantity: (CartItem) -> Unit, decrementQuantity: (CartItem) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 10.dp)
    ) {
        SubcomposeAsyncImage(
            model = item.thumbnailUrl,
            contentDescription = item.title,
            contentScale = ContentScale.FillBounds,
            loading = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            },
            modifier = Modifier.size(100.dp)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(10.dp)
        ) {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "$${item.price}", style = MaterialTheme.typography.titleMedium)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
        ) {
            IconButton(onClick = { incrementQuantity(item) }) {
                Icon(
                    imageVector = CheapacIcons.ArrowDropUp,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(text = item.quantity.toString(), color = MaterialTheme.colorScheme.onBackground)
            IconButton(onClick = { decrementQuantity(item) }) {
                Icon(
                    imageVector = CheapacIcons.ArrowDropDown,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCartItem() {
    CartItem(
        item = CartItem(
            id = 1,
            title = "Test Cart Item",
            thumbnailUrl = "",
            price = 100,
            quantity = 12
        ),
        incrementQuantity = {},
        decrementQuantity = {}
    )
}

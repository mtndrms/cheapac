package com.example.cheapac.presentation.feature.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.R
import com.example.cheapac.data.local.entity.CartItem
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.NothingToListState
import com.example.cheapac.presentation.feature.cart.component.CartItem

@Composable
internal fun CartRoute(
    goBack: () -> Unit,
    viewModel: CartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CartScreen(
        goBack = goBack,
        clear = viewModel::clear,
        uiState = uiState,
        incrementQuantity = viewModel::incrementQuantity,
        decrementQuantity = viewModel::decrementQuantity
    )
}

@Composable
private fun CartScreen(
    goBack: () -> Unit,
    clear: () -> Unit,
    uiState: CartUiState,
    incrementQuantity: (CartItem) -> Unit,
    decrementQuantity: (CartItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = goBack) {
                Icon(imageVector = CheapacIcons.ArrowBack, contentDescription = "go back")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = stringResource(id = R.string.your_cart),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = clear) {
                Icon(imageVector = CheapacIcons.Delete, contentDescription = "clear")
            }
            Spacer(modifier = Modifier.width(10.dp))
        }

        uiState.cart.data?.let { data ->
            SuccessState(
                total = uiState.total,
                data = data,
                incrementQuantity = incrementQuantity,
                decrementQuantity = decrementQuantity
            )
        }

        if (uiState.cart.isLoading) {
            LoadingState(modifier = Modifier)
        }

        uiState.cart.message?.let { message ->
            ErrorState(
                message = message,
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun SuccessState(
    total: Int,
    data: List<CartItem>,
    incrementQuantity: (CartItem) -> Unit,
    decrementQuantity: (CartItem) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (data.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "$${total} in total",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(data) { index, item ->
                        CartItem(
                            item = item,
                            incrementQuantity = incrementQuantity,
                            decrementQuantity = decrementQuantity
                        )

                        if (index < data.lastIndex) {
                            HorizontalDivider()
                        }
                    }
                }
            }

            ExtendedFloatingActionButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(imageVector = CheapacIcons.CartOutlined, contentDescription = "proceed")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Checkout")
            }
        } else {
            NothingToListState(
                label = stringResource(R.string.you_havent_add_anything_to_your_cart_yet),
                imageVector = CheapacIcons.CartOutlined,
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

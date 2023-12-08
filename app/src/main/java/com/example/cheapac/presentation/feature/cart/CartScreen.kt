package com.example.cheapac.presentation.feature.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.data.local.entity.CartItem
import com.example.cheapac.domain.model.Product

@Composable
internal fun CartRoute(viewModel: CartViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CartScreen(uiState = uiState)
}

@Composable
private fun CartScreen(uiState: CartUiState) {
    uiState.cart.data?.let { data ->
        SuccessState(data = data)
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

@Composable
private fun SuccessState(data: CartItem) {

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
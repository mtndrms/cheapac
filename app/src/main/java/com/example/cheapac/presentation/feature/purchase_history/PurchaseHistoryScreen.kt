package com.example.cheapac.presentation.feature.purchase_history

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
import com.example.cheapac.data.local.entity.PurchaseHistoryItem

@Composable
internal fun PurchaseHistoryRoute(viewModel: PurchaseHistoryViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PurchaseHistoryScreen(
        uiState = uiState
    )
}

@Composable
private fun PurchaseHistoryScreen(uiState: PurchaseHistoryUiState) {
    uiState.items.data?.let { data ->
        SuccessState(data = data)
    }

    if (uiState.items.isLoading) {
        LoadingState(modifier = Modifier)
    }

    uiState.items.message?.let { message ->
        ErrorState(message = message, modifier = Modifier)
    }
}

@Composable
private fun SuccessState(data: List<PurchaseHistoryItem>) {

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
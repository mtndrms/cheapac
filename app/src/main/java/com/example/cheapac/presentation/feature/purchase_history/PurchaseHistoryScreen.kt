package com.example.cheapac.presentation.feature.purchase_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PurchaseHistoryRoute(viewModel: PurchaseHistoryViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PurchaseHistoryScreen(
        uiState = uiState
    )
}

@Composable
fun PurchaseHistoryScreen(uiState: PurchaseHistoryUiState) {

}
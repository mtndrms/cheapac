package com.example.cheapac.presentation.feature.purchase_history

import com.example.cheapac.data.UiState
import com.example.cheapac.data.local.entity.PurchaseHistoryItem

data class PurchaseHistoryUiState(
    val items: UiState<List<PurchaseHistoryItem>> = UiState(isLoading = true)
)

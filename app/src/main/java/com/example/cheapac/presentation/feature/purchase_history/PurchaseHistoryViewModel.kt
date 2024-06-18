package com.example.cheapac.presentation.feature.purchase_history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PurchaseHistoryViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(PurchaseHistoryUiState())
    val uiState = _uiState.asStateFlow()
}

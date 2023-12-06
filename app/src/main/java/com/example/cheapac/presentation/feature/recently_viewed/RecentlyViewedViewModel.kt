package com.example.cheapac.presentation.feature.recently_viewed

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RecentlyViewedViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(RecentlyViewedUiState())
    val uiState = _uiState.asStateFlow()
}
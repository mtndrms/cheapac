package com.example.cheapac.presentation.feature.categories

import com.example.cheapac.domain.model.Category
import com.example.cheapac.data.UiState

data class CategoriesUiState(
    val categories: UiState<List<Category>> = UiState(isLoading = true)
)

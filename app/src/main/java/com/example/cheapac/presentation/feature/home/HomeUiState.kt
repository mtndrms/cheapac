package com.example.cheapac.presentation.feature.home

import com.example.cheapac.domain.model.Product

data class HomeUiState(
    val isSignedIn: Boolean = false,
    val isPremium: Boolean = false,
    val highlights: List<Product> = listOf(),
    val products: List<Product> = listOf(),
    val categories: List<Product> = listOf()
)
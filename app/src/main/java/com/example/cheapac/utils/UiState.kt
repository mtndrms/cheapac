package com.example.cheapac.utils

data class UiState<T>(
    val data: T? = null,
    val message: String? = null,
    val isLoading: Boolean = false
)
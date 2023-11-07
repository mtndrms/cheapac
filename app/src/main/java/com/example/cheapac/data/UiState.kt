package com.example.cheapac.data

data class UiState<T>(
    val data: T? = null,
    val message: String? = null,
    val isLoading: Boolean = false
)
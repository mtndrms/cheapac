package com.example.cheapac.presentation.feature.categories

sealed interface CategoriesEvent {
    data object InitialFetch : CategoriesEvent
}

package com.example.cheapac.presentation.feature.review

import com.example.cheapac.data.remote.dto.product.Review

data class ReviewUiState(
    val reviews: List<Review> = emptyList()
)

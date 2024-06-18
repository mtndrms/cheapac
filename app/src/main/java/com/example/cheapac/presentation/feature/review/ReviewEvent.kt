package com.example.cheapac.presentation.feature.review

import com.example.cheapac.data.remote.dto.product.Review

sealed interface ReviewEvent {
    data class InitialFetch(val reviews: List<Review>) : ReviewEvent
    data class SubmitReview(val review: Review) : ReviewEvent
}

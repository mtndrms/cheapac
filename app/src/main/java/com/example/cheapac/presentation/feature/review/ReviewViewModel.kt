package com.example.cheapac.presentation.feature.review

import androidx.lifecycle.ViewModel
import com.example.cheapac.data.remote.dto.product.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ReviewUiState())
    val uiState = _uiState.asStateFlow()

    private fun updateReviews(reviews: List<Review>) {
        _uiState.update {
            it.copy(reviews = reviews)
        }
    }

    private fun submitReview(review: Review) {
        val current = _uiState.value.reviews.toMutableList()
        current.add(review)

        _uiState.update {
            it.copy(reviews = current)
        }
    }

    fun onEvent(event: ReviewEvent) {
        when (event) {
            is ReviewEvent.InitialFetch -> {
                updateReviews(reviews = event.reviews)
            }

            is ReviewEvent.SubmitReview -> {
                submitReview(review = event.review)
            }
        }
    }
}

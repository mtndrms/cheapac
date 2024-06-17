package com.example.cheapac.presentation.feature.review

import android.util.Log
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

    fun updateReviews(reviews: List<Review>) {
        _uiState.update {
            it.copy(reviews = reviews)
        }
    }

    fun submitReview(review: Review) {
        val current = _uiState.value.reviews.toMutableList()
        current.add(review)

        _uiState.update {
            Log.i("ReviewViewModel", "${current.size}")
            it.copy(reviews = current)
        }
        Log.i("ReviewViewModel", "${uiState.value.reviews.size}")
    }
}

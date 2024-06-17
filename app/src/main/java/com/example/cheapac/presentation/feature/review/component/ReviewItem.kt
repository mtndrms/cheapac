package com.example.cheapac.presentation.feature.review.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cheapac.data.remote.dto.product.Review
import com.example.cheapac.presentation.component.RatingBar

@Composable
fun ReviewItem(review: Review, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
            .then(modifier),
    ) {
        RatingBar(rating = review.rating)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = review.comment, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "- ${review.reviewerName}",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleSmall,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = review.date.dropLast(review.date.length - 10),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleSmall,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@PreviewFontScale
private fun PreviewReviewItem() {
    ReviewItem(
        review = Review(
            rating = 4,
            comment = "Lorem ipsum doler sit amet.",
            date = "17-06-2024",
            reviewerEmail = "johndoe@example.com",
            reviewerName = "John Doe"
        )
    )
}

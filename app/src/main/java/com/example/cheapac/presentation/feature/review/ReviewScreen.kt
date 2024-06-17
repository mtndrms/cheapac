package com.example.cheapac.presentation.feature.review

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.R
import com.example.cheapac.data.remote.dto.product.Review
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.NothingToListState
import com.example.cheapac.presentation.component.top_bar.TopBar
import com.example.cheapac.presentation.component.top_bar.TopBarButtonOpts
import com.example.cheapac.presentation.feature.review.component.ReviewItem
import com.example.cheapac.utils.DateTimeUtils

@Composable
internal fun ReviewRoute(
    viewModel: ReviewViewModel = hiltViewModel(),
    reviews: List<Review>,
    goBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ReviewScreen(
        uiState = uiState,
        reviews = reviews,
        submitReview = viewModel::submitReview,
        updateReviews = viewModel::updateReviews,
        goBack = goBack
    )
}

@Composable
private fun ReviewScreen(
    uiState: ReviewUiState,
    reviews: List<Review>,
    submitReview: (Review) -> Unit,
    updateReviews: (List<Review>) -> Unit,
    goBack: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        updateReviews(reviews)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(
            title = stringResource(id = R.string.reviews),
            navigationButtonOpts = TopBarButtonOpts(icon = CheapacIcons.Close, onClick = goBack)
        )

        Reviews(reviews = uiState.reviews, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(16.dp))
        SubmitReview(
            onSubmit = { review ->
                submitReview(review)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun Reviews(reviews: List<Review>, modifier: Modifier = Modifier) {
    if (reviews.isNotEmpty()) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier)
        ) {
            items(items = reviews) { review ->
                ReviewItem(review = review)
            }
        }
    } else {
        NothingToListState(
            label = stringResource(R.string.nothing_to_list_review_screen),
            imageVector = CheapacIcons.Review
        )
    }
}

@Composable
private fun SubmitReview(onSubmit: (Review) -> Unit, modifier: Modifier = Modifier) {
    val value = remember { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp)
            .then(modifier)
    ) {
        BasicTextField(
            value = value.value,
            onValueChange = {
                value.value = it
            },
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(start = 16.dp)
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        innerTextField()
                        if (value.value.isEmpty()) {
                            Text(
                                text = stringResource(R.string.review_input_field_label),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .alpha(0.75f)
                            )
                        }
                    }
                    Row(modifier = Modifier.wrapContentWidth()) {
                        if (value.value.isNotEmpty()) {
                            Icon(
                                imageVector = CheapacIcons.Clear,
                                contentDescription = "clear input field",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .clickable { value.value = "" }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Icon(
                                imageVector = CheapacIcons.Send,
                                contentDescription = "send",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .clickable {
                                        onSubmit(
                                            Review(
                                                rating = 5,
                                                date = DateTimeUtils.now(),
                                                comment = value.value,
                                                reviewerName = "John Doe",
                                                reviewerEmail = "johndoe@example.com"
                                            )
                                        )
                                    }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }
                }
            },
        )
    }
}

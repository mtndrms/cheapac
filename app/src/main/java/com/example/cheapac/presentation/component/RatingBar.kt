package com.example.cheapac.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cheapac.presentation.common.CheapacIcons

const val MAX = 5

@Composable
fun RatingBar(rating: Int, modifier: Modifier = Modifier) {
    Row {
        for (i in 0 until rating) {
            Icon(imageVector = CheapacIcons.Star, contentDescription = "filled")
        }

        for (i in 0 until MAX - rating) {
            Icon(imageVector = CheapacIcons.StarOutlined, contentDescription = "outlined")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRatingBar() {
    RatingBar(rating = 3)
}
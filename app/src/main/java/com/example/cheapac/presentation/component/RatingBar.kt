package com.example.cheapac.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cheapac.presentation.common.CheapacIcons

const val MAX = 5

@Composable
fun RatingBar(rating: Float, modifier: Modifier = Modifier) {
    Row {
        for (i in 0 until rating.toInt()) {
            Icon(imageVector = CheapacIcons.Star, contentDescription = "")
        }

        for (i in 0 until MAX - rating.toInt()) {
            Icon(imageVector = CheapacIcons.StarOutlined, contentDescription = "")
        }
        
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "$rating in 675 ratings")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRatingBar() {
    RatingBar(rating = 3f)
}
package com.example.cheapac.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.cheapac.domain.model.Product

val testHighlights =
        mutableListOf(
                "Men's clothing",
                "Women's clothing",
                "Technology",
                "Books",
                "Education",
                "Smartphones",
                "Market",
                "Outdoor",
                "Cosmetic",
                "Phones",
                "Computers",
                "Fashion"
        )

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HighlightsCarousel(highlights: List<Product>, modifier: Modifier) {
    var pageIndex by remember { mutableIntStateOf(0) }
    val pagerState =
            rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) {
                testHighlights.size
            }

    Box(modifier = Modifier.fillMaxWidth(0.9f).clip(RoundedCornerShape(3.dp)).then(modifier)) {
        HorizontalPager(state = pagerState) { page ->
            pageIndex = pagerState.settledPage + 1

            Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.error)) {
                Text(text = testHighlights[page], modifier = Modifier.align(Alignment.Center))
            }
        }

        Text(
                text = "$pageIndex/${testHighlights.size}",
                modifier = Modifier.padding(10.dp).align(Alignment.BottomEnd)
        )
    }
}

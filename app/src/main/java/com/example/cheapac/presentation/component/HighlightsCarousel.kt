package com.example.cheapac.presentation.component

import android.os.CountDownTimer
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cheapac.domain.model.Product
import com.example.cheapac.data.UiState
import kotlinx.coroutines.launch

val testHighlights = mutableListOf(
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
fun HighlightsCarousel(
    highlights: UiState<List<Product>>,
    autoSwipeDuration: Long,
    modifier: Modifier
) {
    val scope = rememberCoroutineScope()
    var pageIndex by remember { mutableIntStateOf(0) }

    highlights.data?.let { data ->
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f
        ) {
            data.size
        }

        val timer = object : CountDownTimer(autoSwipeDuration, autoSwipeDuration) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                scope.launch {
                    if (pageIndex < pagerState.pageCount - 1) {
                        pageIndex.inc()
                    } else {
                        pageIndex = 0
                    }

                    pagerState.animateScrollToPage(
                        page = pageIndex,
                        pageOffsetFraction = 0f,
                        animationSpec = tween(500)
                    )
                }
            }
        }

        LaunchedEffect(pageIndex) {
            timer.cancel()
            timer.start()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(5.dp))
                .then(modifier)
        ) {
            HorizontalPager(state = pagerState) {
                pageIndex = pagerState.settledPage + 1
                timer.cancel()
                timer.start()

                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data[pageIndex].thumbnail)
                            .crossfade(true)
                            .build(),
                        contentDescription = data[pageIndex].title,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Text(
                text = "$pageIndex/${data.size}",
                color = MaterialTheme.colorScheme.onTertiary,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(5.dp))
                    .alpha(0.5f)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(horizontal = 7.dp, vertical = 3.dp)
            )
        }
    }

    if (highlights.isLoading) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier)
        ) {
            CircularProgressIndicator()
        }
    }

    highlights.message?.let { message ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier)
        ) {
            Text(text = message, color = MaterialTheme.colorScheme.error)
        }
    }
}

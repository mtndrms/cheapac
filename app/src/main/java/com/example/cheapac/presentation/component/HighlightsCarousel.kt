package com.example.cheapac.presentation.component

import android.os.CountDownTimer
import android.util.Log
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
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.cheapac.domain.model.Product
import com.example.cheapac.data.UiState
import kotlinx.coroutines.Job
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
    var timerJob: Job? = null
    var pageIndex by remember { mutableIntStateOf(0) }

    highlights.data?.let { data ->
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f
        ) {
            data.size
        }

        val timer = object : CountDownTimer(autoSwipeDuration, autoSwipeDuration) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("HighlightsCarousel", "onTick")
            }

            override fun onFinish() {
                timerJob = scope.launch {
                    Log.i("HighlightsCarousel", "onFinish")
                    pagerState.animateScrollToPage(
                        page = if (pageIndex + 1 == pagerState.pageCount) 0 else pageIndex + 1,
                        pageOffsetFraction = 0f,
                        animationSpec = tween(500)
                    )
                }
            }
        }

        LaunchedEffect(key1 = true) {
            timer.start()
        }

        LaunchedEffect(key1 = pagerState.settledPage) {
            timerJob?.let { job: Job ->
                job.cancel()
                timer.cancel()
            }

            timer.start()

            // scrolling forward
            pageIndex = if (pageIndex < pagerState.settledPage) {
                pageIndex.inc()
            } else if (pagerState.settledPage < pageIndex) {
                if (pagerState.settledPage != 0) {
                    pageIndex.dec()
                } else 0
            } else 0
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(5.dp))
                .then(modifier)
        ) {
            HorizontalPager(state = pagerState) {
                Box(modifier = Modifier.fillMaxSize()) {
                    SubcomposeAsyncImage(
                        model = data[pageIndex].thumbnail,
                        contentDescription = "highlights",
                        contentScale = ContentScale.FillBounds,
                        loading = {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator()
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Indicator(
                modifier = Modifier.align(Alignment.BottomEnd),
                pageIndex = pageIndex + 1,
                size = data.size
            )
        }
    }

    if (highlights.isLoading) {
        LoadingState(modifier = modifier)
    }

    highlights.message?.let { message ->
        ErrorState(message = message, modifier = modifier)
    }
}

@Composable
fun Indicator(modifier: Modifier, pageIndex: Int, size: Int) {
    Text(
        text = "$pageIndex/${size}",
        color = MaterialTheme.colorScheme.onTertiary,
        style = MaterialTheme.typography.labelMedium,
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(5.dp))
            .alpha(0.5f)
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(horizontal = 7.dp, vertical = 3.dp)
            .then(modifier)
    )
}

@Composable
fun LoadingState(modifier: Modifier) {
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

@Composable
fun ErrorState(message: String, modifier: Modifier) {
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
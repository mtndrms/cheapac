package com.example.cheapac.presentation.feature.recently_viewed

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.cheapac.R
import com.example.cheapac.data.local.entity.RecentlyViewedItem
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.NothingToListState
import com.example.cheapac.utils.capitalize

@Composable
fun RecentlyViewedRoute(goBack: () -> Unit, viewModel: RecentlyViewedViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    RecentlyViewedScreen(goBack = goBack, clearAll = viewModel::clear, uiState = uiState)
}

@Composable
fun RecentlyViewedScreen(goBack: () -> Unit, clearAll: () -> Unit, uiState: RecentlyViewedUiState) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header(
            title = stringResource(id = R.string.recently_viewed),
            goBack = goBack,
            clearAll = clearAll
        )
        uiState.items.data?.let { data ->
            SuccessState(data = data)
        }

        if (uiState.items.isLoading) {
            LoadingState(modifier = Modifier)
        }

        uiState.items.message?.let { message ->
            ErrorState(message = message, modifier = Modifier)
        }
    }
}

@Composable
private fun SuccessState(data: List<RecentlyViewedItem>) {
    if (data.isNotEmpty()) {
        LazyColumn {
            items(items = data) { item: RecentlyViewedItem ->
                RecentlyViewedItem(
                    title = item.title,
                    thumbnailUrl = item.thumbnailUrl,
                    date = item.createdAt.toString()
                )
            }
        }
    } else {
        NothingToListState(
            imageVector = CheapacIcons.History,
            label = stringResource(R.string.you_havent_see_any_product_yet),
            contentDescription = stringResource(R.string.you_havent_see_any_product_yet)
        )
    }
}

@Composable
private fun LoadingState(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(message: String, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun Header(
    title: String,
    goBack: () -> Unit,
    clearAll: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = goBack) {
                Icon(
                    imageVector = CheapacIcons.ArrowBack,
                    contentDescription = "go back",
                    modifier = Modifier.size(32.dp)
                )
            }

            Text(
                text = title.capitalize(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                modifier = Modifier
            )

            IconButton(onClick = clearAll) {
                Icon(
                    imageVector = CheapacIcons.Delete,
                    contentDescription = "clear recently viewed items",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.15f)
        )
    }
}

@Composable
fun RecentlyViewedItem(title: String, thumbnailUrl: String, date: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .height(100.dp)
            .padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = thumbnailUrl,
                contentDescription = "product thumbnail",
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
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(75.dp)
                    .clip(shape = CircleShape)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = date,
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }
}
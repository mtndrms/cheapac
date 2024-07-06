package com.example.cheapac.presentation.feature.recently_viewed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.R
import com.example.cheapac.data.local.entity.RecentlyViewedItem as RecentlyViewedItemModel
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.NothingToListState
import com.example.cheapac.presentation.component.top_bar.TopBar
import com.example.cheapac.presentation.component.top_bar.TopBarButtonOpts
import com.example.cheapac.presentation.feature.recently_viewed.component.RecentlyViewedItem

@Composable
internal fun RecentlyViewedRoute(
    goBack: () -> Unit,
    viewModel: RecentlyViewedViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RecentlyViewedScreen(
        goBack = goBack,
        onEvent = viewModel::onEvent,
        uiState = uiState
    )
}

@Composable
private fun RecentlyViewedScreen(
    goBack: () -> Unit,
    onEvent: (RecentlyViewedEvent) -> Unit,
    uiState: RecentlyViewedUiState
) {
    LaunchedEffect(key1 = Unit) {
        onEvent(RecentlyViewedEvent.InitialFetch)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        uiState.items.data?.let { data ->
            TopBar(
                title = stringResource(id = R.string.recently_viewed),
                navigationButtonOpts = TopBarButtonOpts(
                    icon = CheapacIcons.ArrowBack,
                    onClick = goBack
                ),
                primaryButtonOpts = TopBarButtonOpts(
                    icon = CheapacIcons.DeleteOutlined,
                    onClick = {
                        onEvent(RecentlyViewedEvent.Clear)
                    },
                    shouldHideButton = data.isEmpty()
                ),
            )
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
private fun SuccessState(data: List<RecentlyViewedItemModel>) {
    if (data.isNotEmpty()) {
        LazyColumn {
            items(items = data) { item: RecentlyViewedItemModel ->
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

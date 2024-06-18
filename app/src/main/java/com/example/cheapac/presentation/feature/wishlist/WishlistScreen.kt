package com.example.cheapac.presentation.feature.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.cheapac.data.local.entity.WishlistItem
import com.example.cheapac.presentation.feature.wishlist.component.WishlistItem as WishlistItemComponent
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.NothingToListState
import com.example.cheapac.presentation.component.top_bar.TopBar
import com.example.cheapac.presentation.component.top_bar.TopBarButtonOpts

@Composable
internal fun WishlistRoute(goBack: () -> Unit, viewModel: WishlistViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WishlistScreen(
        goBack = goBack,
        onEvent = viewModel::onEvent,
        uiState = uiState
    )
}

@Composable
private fun WishlistScreen(
    uiState: WishlistUiState,
    goBack: () -> Unit,
    onEvent: (WishlistEvent) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        onEvent(WishlistEvent.InitialFetch)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        uiState.items.data?.let { data ->
            TopBar(
                title = stringResource(id = R.string.wishlist),
                navigationButtonOpts = TopBarButtonOpts(
                    icon = CheapacIcons.ArrowBack,
                    onClick = goBack
                ),
                primaryButtonOpts = TopBarButtonOpts(
                    icon = CheapacIcons.DeleteOutlined,
                    onClick = { onEvent(WishlistEvent.Clear) },
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
private fun SuccessState(data: List<WishlistItem>) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (data.isNotEmpty()) {
            LazyColumn {
                items(items = data) { item: WishlistItem ->
                    WishlistItemComponent(
                        title = item.title,
                        thumbnailUrl = item.thumbnailUrl,
                        note = item.note
                    )
                }
            }
        } else {
            NothingToListState(
                imageVector = CheapacIcons.Favorite,
                label = stringResource(id = R.string.you_havent_added_anything_to_your_wish_list_yet),
            )
        }
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
            .verticalScroll(rememberScrollState())
            .then(modifier)
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}

package com.example.cheapac.presentation.feature.wishlist

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.cheapac.R
import com.example.cheapac.data.local.entity.WishlistItem
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.NothingToListState
import com.example.cheapac.utils.capitalize
import com.example.cheapac.utils.handleShareProductClick

@Composable
internal fun WishlistRoute(goBack: () -> Unit, viewModel: WishlistViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WishlistScreen(
        goBack = goBack,
        clearAll = viewModel::clearAll,
        uiState = uiState
    )
}

@Composable
private fun WishlistScreen(uiState: WishlistUiState, goBack: () -> Unit, clearAll: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Header(title = stringResource(id = R.string.wishlist), goBack = goBack, clearAll = clearAll)
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
private fun SuccessState(data: List<WishlistItem>) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (data.isNotEmpty()) {
            LazyColumn {
                items(items = data) { item: WishlistItem ->
                    WishlistItem(
                        title = item.title,
                        thumbnailUrl = item.thumbnailUrl,
                        note = item.note,
                        date = item.dateAdded.toString()
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

@Composable
private fun Header(
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
                    contentDescription = "clear wishlist",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}

@Composable
private fun WishlistItem(title: String, thumbnailUrl: String, note: String, date: String) {
    var isExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Spacer(modifier = Modifier.height(10.dp))
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .height(if (!isExpanded) 100.dp else 255.dp)
            .padding(horizontal = 10.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
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
                    modifier = Modifier.size(100.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1f, true)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "A note for myself; ",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleSmall,
                        )

                        if (!isExpanded) {
                            Text(
                                text = note.repeat(100),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                }

                IconButton(
                    onClick = { isExpanded = isExpanded.not() },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = if (isExpanded) CheapacIcons.ArrowDropUp else CheapacIcons.ArrowDropDown,
                        contentDescription = "expand wishlist card"
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = note.repeat(100),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .height(90.dp)
            )

            Row(modifier = Modifier.align(Alignment.End)) {
                TextButton(onClick = { }) {
                    Text(text = stringResource(R.string.remove))
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = { handleShareProductClick(context = context, title = title) }) {
                    Text(text = stringResource(id = R.string.share))
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WishlistItemPreview() {
    WishlistItem(title = "iPhone X", "", "Lorem ipsum".repeat(100), "01/01/2024")
}

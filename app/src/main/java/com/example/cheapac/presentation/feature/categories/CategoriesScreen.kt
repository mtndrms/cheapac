package com.example.cheapac.presentation.feature.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.R
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.top_bar.TopBar
import com.example.cheapac.presentation.component.top_bar.TopBarButtonOpts
import com.example.cheapac.presentation.feature.categories.component.CategoryRow

@Composable
internal fun CategoriesRoute(
    goBack: () -> Unit,
    navigateToCategory: (code: String, title: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CategoriesScreen(
        goBack = goBack,
        navigateToCategory = navigateToCategory,
        onEvent = viewModel::onEvent,
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
private fun CategoriesScreen(
    goBack: () -> Unit,
    navigateToCategory: (code: String, title: String) -> Unit,
    onEvent: (CategoriesEvent) -> Unit,
    modifier: Modifier,
    uiState: CategoriesUiState
) {
    LaunchedEffect(key1 = Unit) {
        onEvent(CategoriesEvent.InitialFetch)
    }

    uiState.categories.data?.let { data ->
        Column {
            TopBar(
                title = stringResource(id = R.string.categories),
                navigationButtonOpts = TopBarButtonOpts(
                    icon = CheapacIcons.ArrowBack,
                    onClick = goBack
                ),
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(data) {
                    CategoryRow(category = it, navigateToCategory = navigateToCategory)
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(start = 64.dp, end = 10.dp)
                            .alpha(0.2f),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }

    if (uiState.categories.isLoading) {
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

    uiState.categories.message?.let { message ->
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
}

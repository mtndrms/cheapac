package com.example.cheapac.presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.presentation.component.CategoriesCatalog
import com.example.cheapac.presentation.component.HighlightsCarousel
import com.example.cheapac.presentation.component.HorizontalProducts

@Composable
internal fun HomeRoute(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
internal fun HomeScreen(modifier: Modifier, uiState: HomeUiState) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .then(modifier)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HighlightsCarousel(highlights = uiState.highlights, autoSwipeDuration = 3000,modifier = Modifier.height(128.dp))
            Spacer(modifier = Modifier.height(20.dp))
            CategoriesCatalog(categories = uiState.categories, modifier = Modifier)
            Spacer(modifier = Modifier.height(20.dp))
            HighlightsCarousel(highlights = uiState.highlights, autoSwipeDuration = 5000, modifier = Modifier.height(192.dp))
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalProducts(products = uiState.highlights, modifier = Modifier)
        }
    }
}

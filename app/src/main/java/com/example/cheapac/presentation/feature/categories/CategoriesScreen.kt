package com.example.cheapac.presentation.feature.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.R
import com.example.cheapac.domain.model.Category
import com.example.cheapac.presentation.common.CheapacIcons

@Composable
internal fun CategoriesRoute(
    navigateToCategory: (code: String, title: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CategoriesScreen(navigateToCategory, modifier, uiState)
}

@Composable
internal fun CategoriesScreen(
    navigateToCategory: (code: String, title: String) -> Unit,
    modifier: Modifier,
    uiState: CategoriesUiState
) {
    uiState.categories.data?.let { data ->
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(data) {
                CategoriesRow(category = it, navigateToCategory = navigateToCategory)
                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .padding(start = 64.dp, end = 10.dp)
                        .alpha(0.2f)
                )
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

@Composable
private fun CategoriesRow(
    category: Category,
    navigateToCategory: (code: String, title: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable { navigateToCategory(category.code, category.title) }
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = category.iconId),
            contentDescription = stringResource(id = R.string.categories),
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = category.title.replace("-", " "),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCategoriesRow() {
    CategoriesRow(
        category = Category(
            code = "mens-watches",
            "Men's Watches",
            iconId = CheapacIcons.MenWatches.id
        ),
        navigateToCategory = { _, _ -> }
    )
}

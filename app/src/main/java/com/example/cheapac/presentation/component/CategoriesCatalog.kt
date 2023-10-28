package com.example.cheapac.presentation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cheapac.R
import com.example.cheapac.domain.model.Category
import com.example.cheapac.utils.UiState

private const val ROW = 2
private const val COLUMN = 5
private val testCategories = mutableListOf(
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

@Composable
fun CategoriesCatalog(
    categories: UiState<List<Category>>,
    navigateToCategories: () -> Unit,
    navigateToCategory: (String) -> Unit,
    modifier: Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val horizontalPadding = screenWidth * 0.05f
    val cardSize = screenWidth / COLUMN
    val spaceBetweenElements = 5.dp

    Text(
        text = stringResource(R.string.categories),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = horizontalPadding)
    )

    Spacer(modifier = Modifier.height(5.dp))

    categories.data?.let { data ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(COLUMN),
            verticalArrangement = Arrangement.spacedBy(spaceBetweenElements),
            horizontalArrangement = Arrangement.spacedBy(spaceBetweenElements),
            modifier = Modifier
                .height(cardSize * 2 + spaceBetweenElements)
                .padding(horizontal = horizontalPadding)
                .then(modifier)
        ) {
            if (data.isNotEmpty()) {
                items(data.subList(0, ROW * COLUMN).size) {
                    Column(
                        modifier = Modifier
                            .size(cardSize)
                            .clip(RoundedCornerShape(5.dp))
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                            .clickable {
                                if (it + 1 < ROW * COLUMN) {
                                    navigateToCategory(data[it].code)
                                } else {
                                    navigateToCategories()
                                }
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = data[it].iconId),
                            contentDescription = data[it].title
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = if (it + 1 < ROW * COLUMN) {
                                data[it].title.replaceFirst("-", "\n")
                            } else {
                                stringResource(id = R.string.see_all)
                            },
                            textAlign = TextAlign.Center,
                            maxLines = if (!data[it].title.contains("-")) 1 else 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }
    }

    if (categories.isLoading) {
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

    categories.message?.let { message ->
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

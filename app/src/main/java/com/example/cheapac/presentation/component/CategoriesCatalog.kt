package com.example.cheapac.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cheapac.R
import com.example.cheapac.domain.model.Product

private const val ROW = 2
private const val COLUMN = 4
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
fun CategoriesCatalog(categories: List<Product>, modifier: Modifier) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val horizontalPadding = screenWidth * 0.05f
    val cardSize = screenWidth / COLUMN
    val spaceBetweenElements = 5.dp

    LazyVerticalGrid(
        columns = GridCells.Fixed(COLUMN),
        verticalArrangement = Arrangement.spacedBy(spaceBetweenElements),
        horizontalArrangement = Arrangement.spacedBy(spaceBetweenElements),
        modifier = Modifier
            .height(cardSize * 2 + spaceBetweenElements)
            .padding(horizontal = horizontalPadding)
            .then(modifier)
    ) {
        if (testCategories.isNotEmpty()) {
            items(testCategories.subList(0, ROW * COLUMN).size) {
                Column(
                    modifier = Modifier
                        .size(cardSize)
                        .clip(RoundedCornerShape(3.dp))
                        .background(MaterialTheme.colorScheme.error),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (it + 1 < ROW * COLUMN) {
                        Text(
                            text = testCategories[it],
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onError
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.all),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onError
                        )
                    }
                }
            }
        }
    }
}

package com.example.cheapac.presentation.feature.categories.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cheapac.R
import com.example.cheapac.domain.model.Category
import com.example.cheapac.presentation.common.CheapacIcons

@Composable
fun CategoryRow(
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
    CategoryRow(
        category = Category(
            code = "mens-watches",
            "Men's Watches",
            iconId = CheapacIcons.MenWatches.id
        ),
        navigateToCategory = { _, _ -> }
    )
}

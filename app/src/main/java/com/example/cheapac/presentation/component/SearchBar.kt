package com.example.cheapac.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp
import com.example.cheapac.R
import com.example.cheapac.presentation.common.CheapacIcons

@Composable
fun SearchBar(modifier: Modifier = Modifier, query: String = "", onSearchClick: (String) -> Unit) {
    val value = remember { mutableStateOf(query) }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(5.dp)
            )
            .then(modifier)
    ) {
        BasicTextField(
            value = value.value,
            onValueChange = {
                value.value = it
            },
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(start = 16.dp)
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        innerTextField()
                        if (value.value.isEmpty()) {
                            Text(
                                text = stringResource(R.string.search_box_hint),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .alpha(0.75f)
                            )
                        }
                    }
                    Row(modifier = Modifier.wrapContentWidth()) {
                        if (value.value.isNotEmpty()) {
                            Icon(
                                imageVector = CheapacIcons.Clear,
                                contentDescription = "clear search bar input",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .clickable { value.value = "" }
                            )
                            Icon(
                                imageVector = CheapacIcons.Search,
                                contentDescription = "search",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .clickable { onSearchClick(value.value) }
                            )
                        }
                    }
                }
            },
        )
    }
}

@Composable
@PreviewFontScale
private fun PreviewSearchBar() {
    SearchBar(query = "Preview search bar input", onSearchClick = {})
}

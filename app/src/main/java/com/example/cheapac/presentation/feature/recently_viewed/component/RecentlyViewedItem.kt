package com.example.cheapac.presentation.feature.recently_viewed.component

import androidx.compose.animation.animateContentSize
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
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.cheapac.R
import com.example.cheapac.utils.DateTimeUtils
import com.example.cheapac.utils.handleShareProductClick

/**
 * A Composable function that represents a recently viewed item.
 *
 * @param title The title of the recently viewed item.
 * @param thumbnailUrl The URL of the thumbnail image for the recently viewed item.
 * @param date The date when the item was last viewed.
 */
@Composable
fun RecentlyViewedItem(title: String, thumbnailUrl: String, date: String) {
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
            .height(if (!isExpanded) 100.dp else 125.dp)
            .padding(horizontal = 10.dp),
        onClick = {
            isExpanded = isExpanded.not()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
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
                modifier = Modifier
                    .animateContentSize()
                    .size(if (!isExpanded) 100.dp else 125.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 12.5.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = DateTimeUtils.format(input = date),
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )

                if (isExpanded) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Button(
                        onClick = { handleShareProductClick(context = context, title = title) },
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(end = 12.5.dp)
                    ) {
                        Text(text = stringResource(id = R.string.share))
                    }
                }
            }
        }
    }
}

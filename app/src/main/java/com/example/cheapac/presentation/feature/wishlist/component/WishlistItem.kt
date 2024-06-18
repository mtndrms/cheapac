package com.example.cheapac.presentation.feature.wishlist.component

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.cheapac.R
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.utils.handleShareProductClick

@Composable
fun WishlistItem(title: String, thumbnailUrl: String, note: String) {
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
    WishlistItem(
        title = "iPhone X",
        "",
        "Lorem ipsum".repeat(100)
    )
}

package com.example.cheapac.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.utils.applyDiscount

@Composable
fun ProductCard(
    id: Int,
    title: String,
    price: Int,
    imageUrl: String,
    discountRate: Int,
    navigateToProductDetail: (Int) -> Unit
) {
    Surface(
        shadowElevation = 1.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(180.dp)
            .height(300.dp)
            .clickable {
                navigateToProductDetail(id)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = true)
                    .padding(horizontal = 15.dp, vertical = 10.dp)
            ) {
                SubcomposeAsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.FillBounds,
                    loading = {
                        CircularProgressIndicator()
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )

                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopEnd)
                        .alpha(0.5f)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.tertiary)
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = CheapacIcons.FavoriteOutlined,
                        contentDescription = "favorite",
                        tint = MaterialTheme.colorScheme.onTertiary,
                        modifier = Modifier.padding(3.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .weight(1f, fill = true),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                ) {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    if (discountRate > 1) {
                        Row {
                            Text(
                                text = "$$price",
                                textDecoration = TextDecoration.LineThrough,
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "$discountRate%",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Icon(
                                imageVector = CheapacIcons.ArrowDown,
                                contentDescription = "discount icon",
                                tint = Color.Green
                            )
                        }

                        Text(
                            text = "$${price.applyDiscount(discountRate).toInt()}",
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 18.sp
                        )
                    }
                }

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector = CheapacIcons.CartOutlined,
                        contentDescription = "add to cart"
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Add to cart", maxLines = 1)
                }
            }
        }
    }
}

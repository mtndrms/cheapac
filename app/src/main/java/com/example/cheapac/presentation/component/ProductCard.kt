package com.example.cheapac.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.cheapac.R
import com.example.cheapac.domain.model.Product
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.utils.applyDiscount

@Composable
fun ProductCard(
    id: Int,
    title: String,
    price: Int,
    imageUrl: String,
    discountRate: Int,
    isInStock: Boolean = true,
    navigateToProductDetail: (Int) -> Unit,
    addToWishlist: (Int, String, String, String) -> Unit
) {
    Surface(
        shadowElevation = 1.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .clickable {
                navigateToProductDetail(id)
            }
    ) {
        Column(
            modifier = Modifier
                .width(170.dp)
                .height(300.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = true)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                SubcomposeAsyncImage(
                    model = imageUrl,
                    contentDescription = title,
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
                        .fillMaxSize()
                )

                IconButton(
                    onClick = { addToWishlist(id, title, imageUrl, "") },
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.TopEnd)
                        .alpha(0.8f)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = CheapacIcons.FavoriteOutlined,
                        contentDescription = "favorite",
                        tint = Color.White,
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

                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        Text(
                            text = "$$price",
                            textDecoration = if (discountRate > 0) TextDecoration.LineThrough else TextDecoration.None,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = if (discountRate > 0) 16.sp else 18.sp
                        )
                        if (discountRate > 0) {
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
                    }

                    if (discountRate > 0) {
                        Text(
                            text = "$${price.applyDiscount(discountRate).toInt()}",
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 18.sp
                        )
                    }
                }

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isInStock) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.error
                        },
                        contentColor = if (isInStock) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onError
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(Alignment.CenterHorizontally)
                ) {
                    if (isInStock) {
                        Icon(
                            imageVector = CheapacIcons.CartOutlined,
                            contentDescription = "add to cart"
                        )
                    }

                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = stringResource(
                            id = if (isInStock) {
                                R.string.add_to_cart
                            } else {
                                R.string.notify_me
                            }
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

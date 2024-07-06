package com.example.cheapac.presentation.feature.product_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.cheapac.R
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.utils.applyDiscount

private const val BOTTOM_BAR_HEIGHT = 96

@Composable
fun BottomBarProductDetail(
    price: Int,
    discountRate: Int,
    isInStock: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(BOTTOM_BAR_HEIGHT.dp)
            .background(MaterialTheme.colorScheme.surface)
            .then(modifier)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                Column {
                    if (discountRate > 1) {
                        Row {
                            Text(
                                text = "$${price}",
                                color = MaterialTheme.colorScheme.onSurface,
                                textDecoration = TextDecoration.LineThrough,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "$discountRate%",
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Icon(
                                imageVector = CheapacIcons.ArrowDown,
                                contentDescription = "discount icon",
                                tint = Color.Green
                            )
                        }
                    }

                    Text(
                        text = "$${price.applyDiscount(discountRate)}",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                if (isInStock) {
                    Button(onClick = { }) {
                        Icon(
                            imageVector = CheapacIcons.CartOutlined,
                            contentDescription = stringResource(
                                id = R.string.add_to_cart
                            ).lowercase()
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = stringResource(id = R.string.add_to_cart),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                } else {
                    Button(onClick = { }) {
                        Text(text = stringResource(R.string.notify_me))
                    }
                }
            }
        }
    }
}

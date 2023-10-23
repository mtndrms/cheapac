package com.example.cheapac.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cheapac.presentation.common.CheapacIcons

@Composable
fun ProductCard(title: String, price: Int, imageUrl: String, discountRate: Int = 0) {
    Surface(
        shadowElevation = 1.dp,
        tonalElevation = 1.dp,
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .width(150.dp)
            .height(250.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxHeight(0.5f)) {
                Image(
                    imageVector = CheapacIcons.Cart,
                    contentDescription = "Product thumbnail",
                    modifier = Modifier.fillMaxSize()
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
                    .fillMaxSize()
                    .padding(vertical = 5.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                ) {
                    Text(text = "Product title", style = MaterialTheme.typography.titleLarge)
                    Text(text = "1234.5678 TL", style = MaterialTheme.typography.labelLarge, fontSize = 16.sp)
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Add to cart")
                }
            }
        }
    }
}

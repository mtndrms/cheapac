package com.example.cheapac.presentation.feature.product_detail.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.SubcomposeAsyncImage
import com.example.cheapac.R
import com.example.cheapac.domain.model.Product
import com.example.cheapac.presentation.common.CheapacIcons

@Composable
fun CollapsingToolbar(
    product: Product,
    goBack: () -> Unit,
    visibilityState: MutableTransitionState<Boolean>,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        IconButton(
            onClick = goBack,
            modifier = Modifier
                .zIndex(1f)
                .padding(10.dp)
                .align(Alignment.TopStart)
                .alpha(0.8f)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .size(36.dp)
        ) {
            Icon(
                imageVector = CheapacIcons.ArrowBack,
                contentDescription = "go back",
                tint = Color.White
            )
        }

        AnimatedVisibility(
            visibleState = visibilityState,
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier
                .zIndex(1f)
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (product.stock != 0) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )
                .padding(vertical = 5.dp, horizontal = 10.dp)
        ) {
            Text(
                text = if (product.stock != 0) {
                    stringResource(R.string.x_in_stock, product.stock)
                } else {
                    stringResource(id = R.string.out_of_stock)
                },
                color = if (product.stock != 0) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onError
                }
            )
        }

        SubcomposeAsyncImage(
            model = product.thumbnail,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            loading = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

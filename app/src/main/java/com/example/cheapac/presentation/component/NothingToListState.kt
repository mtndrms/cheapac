package com.example.cheapac.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NothingToListState(
    imageVector: ImageVector? = null,
    @DrawableRes iconResId: Int? = null,
    label: String,
    enableRotation: Boolean = false
) {
    var rotated by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 5000
            },
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    if (enableRotation) {
        LaunchedEffect(true) {
            rotated = rotated.not()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imageVector?.let {
            Icon(
                imageVector = it,
                contentDescription = label,
                modifier = Modifier
                    .size(200.dp)
                    .alpha(0.1f)
                    .graphicsLayer(rotationY = if (rotated) rotation else 0f)
            )
        }

        iconResId?.let { resId ->
            Icon(
                painter = painterResource(id = resId),
                contentDescription = label,
                modifier = Modifier
                    .size(200.dp)
                    .alpha(0.1f)
                    .graphicsLayer(rotationY = if (rotated) rotation else 0f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.alpha(0.2f)
        )
    }
}
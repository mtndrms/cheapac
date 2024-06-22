package com.example.cheapac.presentation.feature.cart.checkout.result

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cheapac.presentation.common.CheapacIcons

@Composable
fun CheckoutResultPage(result: Boolean, modifier: Modifier = Modifier) {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .then(modifier)
    ) {
        AnimatedVisibility(
            visibleState = state,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            if (result) {
                Icon(
                    imageVector = CheapacIcons.Done,
                    contentDescription = "checkout successful",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .size(150.dp)
                        .background(
                            color = Color.Green,
                            shape = CircleShape
                        )
                )
            } else {
                Icon(
                    imageVector = CheapacIcons.Close,
                    contentDescription = "checkout failed",
                    tint = MaterialTheme.colorScheme.onError,
                    modifier = Modifier
                        .size(150.dp)
                        .background(
                            color = MaterialTheme.colorScheme.error,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewCheckoutResultPageSuccess() {
    CheckoutResultPage(result = true)
}

@Composable
@Preview(showBackground = true)
private fun PreviewCheckoutResultPageError() {
    CheckoutResultPage(result = false)
}

package com.example.cheapac.presentation.feature.cart.checkout.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.feature.cart.checkout.common.Step

@Composable
fun ProgressIndicator(
    steps: List<Step>,
    done: MutableSet<Step>,
    activeStep: Step,
    onStepClick: (Step) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        steps.forEachIndexed { index, step ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onStepClick(step) }
            ) {
                if (!done.contains(step)) {
                    Text(
                        text = index.inc().toString(),
                        textAlign = TextAlign.Center,
                        color = if (step == activeStep) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        },
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = if (step == activeStep) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.primaryContainer
                                },
                                shape = CircleShape
                            )
                    )
                } else {
                    Icon(
                        imageVector = CheapacIcons.Done,
                        contentDescription = "step completed"
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = step.title)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@PreviewFontScale
private fun PreviewProgressBar() {
    ProgressIndicator(
        steps = Step.entries.toList(),
        done = mutableSetOf(Step.SHIPPING),
        activeStep = Step.PAYMENT,
        onStepClick = {}
    )
}

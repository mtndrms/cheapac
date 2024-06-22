package com.example.cheapac.presentation.feature.cart.checkout

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cheapac.presentation.feature.cart.CartEvent
import com.example.cheapac.presentation.feature.cart.checkout.common.Step
import com.example.cheapac.presentation.feature.cart.checkout.component.ProgressIndicator
import com.example.cheapac.presentation.feature.cart.checkout.payment.PaymentEvent
import com.example.cheapac.presentation.feature.cart.checkout.payment.PaymentInfo
import com.example.cheapac.presentation.feature.cart.checkout.payment.PaymentPage
import com.example.cheapac.presentation.feature.cart.checkout.result.CheckoutResultPage
import com.example.cheapac.presentation.feature.cart.checkout.review.ReviewPage
import com.example.cheapac.presentation.feature.cart.checkout.shipping.ShippingEvent
import com.example.cheapac.presentation.feature.cart.checkout.shipping.ShippingInfo
import com.example.cheapac.presentation.feature.cart.checkout.shipping.ShippingPage
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutBottomSheet(
    isVisible: Boolean,
    state: SheetState,
    shippingInfo: ShippingInfo,
    paymentInfo: PaymentInfo,
    dismiss: () -> Unit,
    onCartEvent: (CartEvent) -> Unit,
    onShippingEvent: (ShippingEvent) -> Unit,
    onPaymentEvent: (PaymentEvent) -> Unit
) {
    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                dismiss()
            },
            sheetState = state,
        ) {
            BottomSheetContent(
                onCartEvent = onCartEvent,
                onShippingEvent = onShippingEvent,
                onPaymentEvent = onPaymentEvent,
                shippingInfo = shippingInfo,
                paymentInfo = paymentInfo
            )
        }
    }
}

@Composable
private fun BottomSheetContent(
    shippingInfo: ShippingInfo,
    paymentInfo: PaymentInfo,
    onCartEvent: (CartEvent) -> Unit,
    onShippingEvent: (ShippingEvent) -> Unit,
    onPaymentEvent: (PaymentEvent) -> Unit
) {
    val steps = remember { Step.entries.toList() }
    val completedSteps = remember { mutableSetOf<Step>() }
    var activeStep by remember { mutableStateOf(steps[0]) }
    var isDone by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        if (isDone) {
            CheckoutResultPage(result = Random.nextBoolean())
        } else {
            ProgressIndicator(
                steps = steps,
                done = completedSteps,
                activeStep = activeStep,
                onStepClick = {
                    activeStep = it
                }
            )

            when (activeStep) {
                Step.SHIPPING -> {
                    ShippingPage(
                        markAsDone = {
                            completedSteps.add(activeStep)
                            activeStep = Step.PAYMENT
                            isDone = steps.size == completedSteps.size
                        },
                        uiState = shippingInfo,
                        onEvent = onShippingEvent
                    )
                }

                Step.PAYMENT -> {
                    PaymentPage(
                        markAsDone = {
                            completedSteps.add(activeStep)
                            activeStep = Step.REVIEW
                            isDone = steps.size == completedSteps.size
                        },
                        uiState = paymentInfo,
                        onEvent = onPaymentEvent
                    )
                }

                Step.REVIEW -> {
                    ReviewPage(
                        proceed = {
                            completedSteps.add(activeStep)
                            isDone = steps.size == completedSteps.size
                            onCartEvent(CartEvent.Clear)
                        },
                        shippingInfo = shippingInfo,
                        paymentInfo = paymentInfo
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewCheckoutBottomSheet() {
    BottomSheetContent(
        onCartEvent = {},
        onShippingEvent = {},
        onPaymentEvent = {},
        shippingInfo = ShippingInfo(),
        paymentInfo = PaymentInfo()
    )
}

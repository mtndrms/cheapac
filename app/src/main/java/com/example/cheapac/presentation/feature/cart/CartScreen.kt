package com.example.cheapac.presentation.feature.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cheapac.R
import com.example.cheapac.data.local.entity.CartItem
import com.example.cheapac.presentation.common.CheapacIcons
import com.example.cheapac.presentation.component.NothingToListState
import com.example.cheapac.presentation.component.top_bar.TopBar
import com.example.cheapac.presentation.component.top_bar.TopBarButtonOpts
import com.example.cheapac.presentation.feature.cart.checkout.CheckoutBottomSheet
import com.example.cheapac.presentation.feature.cart.checkout.payment.PaymentEvent
import com.example.cheapac.presentation.feature.cart.checkout.payment.PaymentInfo
import com.example.cheapac.presentation.feature.cart.checkout.shipping.ShippingEvent
import com.example.cheapac.presentation.feature.cart.checkout.shipping.ShippingInfo
import com.example.cheapac.presentation.feature.cart.component.CartItem

@Composable
internal fun CartRoute(
    goBack: () -> Unit,
    viewModel: CartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CartScreen(
        goBack = goBack,
        onEvent = viewModel::onEvent,
        onShippingEvent = viewModel::onEvent,
        onPaymentEvent = viewModel::onEvent,
        uiState = uiState,
    )
}

@Composable
private fun CartScreen(
    goBack: () -> Unit,
    onEvent: (CartEvent) -> Unit,
    onShippingEvent: (ShippingEvent) -> Unit,
    onPaymentEvent: (PaymentEvent) -> Unit,
    uiState: CartUiState,
) {
    LaunchedEffect(key1 = Unit) {
        onEvent(CartEvent.InitialFetch)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        uiState.cart.data?.let { data ->
            TopBar(
                title = stringResource(id = R.string.your_cart),
                navigationButtonOpts = TopBarButtonOpts(
                    icon = CheapacIcons.ArrowBack,
                    onClick = goBack
                ),
                primaryButtonOpts = TopBarButtonOpts(
                    icon = CheapacIcons.DeleteOutlined,
                    onClick = {
                        onEvent(CartEvent.Clear)
                    },
                    shouldHideButton = data.isEmpty(),
                )
            )

            SuccessState(
                total = uiState.total,
                data = data,
                shippingInfo = uiState.shippingInfo,
                paymentInfo = uiState.paymentInfo,
                incrementQuantity = { item ->
                    onEvent(CartEvent.IncrementQuantity(cartItem = item))
                },
                decrementQuantity = { item ->
                    onEvent(CartEvent.DecrementQuantity(cartItem = item))
                },
                onCartEvent = onEvent,
                onShippingEvent = onShippingEvent,
                onPaymentEvent = onPaymentEvent
            )
        }

        if (uiState.cart.isLoading) {
            LoadingState(modifier = Modifier)
        }

        uiState.cart.message?.let { message ->
            ErrorState(
                message = message,
                modifier = Modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SuccessState(
    total: Int,
    data: List<CartItem>,
    shippingInfo: ShippingInfo,
    paymentInfo: PaymentInfo,
    incrementQuantity: (CartItem) -> Unit,
    decrementQuantity: (CartItem) -> Unit,
    onCartEvent: (CartEvent) -> Unit,
    onShippingEvent: (ShippingEvent) -> Unit,
    onPaymentEvent: (PaymentEvent) -> Unit,
) {
    val checkoutSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var checkoutBottomSheetVisibility by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (data.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "$${total} in total",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(data) { index, item ->
                        CartItem(
                            item = item,
                            incrementQuantity = incrementQuantity,
                            decrementQuantity = decrementQuantity
                        )

                        if (index < data.lastIndex) {
                            HorizontalDivider()
                        }
                    }
                }
            }

            ExtendedFloatingActionButton(
                onClick = { checkoutBottomSheetVisibility = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(imageVector = CheapacIcons.CartOutlined, contentDescription = "proceed")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Checkout")
            }
        } else {
            NothingToListState(
                label = stringResource(R.string.you_havent_add_anything_to_your_cart_yet),
                imageVector = CheapacIcons.CartOutlined,
            )
        }

        CheckoutBottomSheet(
            isVisible = checkoutBottomSheetVisibility,
            state = checkoutSheetState,
            shippingInfo = shippingInfo,
            paymentInfo = paymentInfo,
            dismiss = { checkoutBottomSheetVisibility = false },
            onCartEvent = onCartEvent,
            onShippingEvent = onShippingEvent,
            onPaymentEvent = onPaymentEvent,
        )
    }
}

@Composable
private fun LoadingState(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(message: String, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}

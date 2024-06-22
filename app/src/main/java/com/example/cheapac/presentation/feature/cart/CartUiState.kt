package com.example.cheapac.presentation.feature.cart

import com.example.cheapac.data.UiState
import com.example.cheapac.data.local.entity.CartItem
import com.example.cheapac.presentation.feature.cart.checkout.payment.PaymentInfo
import com.example.cheapac.presentation.feature.cart.checkout.shipping.ShippingInfo

data class CartUiState(
    val cart: UiState<List<CartItem>> = UiState(isLoading = true),
    val shippingInfo: ShippingInfo = ShippingInfo(),
    val paymentInfo: PaymentInfo = PaymentInfo()
) {
    val total: Int
        get() = cart.data?.sumOf { it.price * it.quantity } ?: 0
}

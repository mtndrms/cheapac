package com.example.cheapac.presentation.feature.cart.checkout.payment

data class PaymentInfo(
    val cardHolder: String = "",
    val cardNumber: String = "",
    val cvc: String = "",
    val expirationDate: String = "",
)

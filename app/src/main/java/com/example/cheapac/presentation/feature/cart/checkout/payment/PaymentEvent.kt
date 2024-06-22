package com.example.cheapac.presentation.feature.cart.checkout.payment

sealed interface PaymentEvent {
    data class CardHolderChanged(val cardHolder: String) : PaymentEvent
    data class CardNumberChanged(val cardNumber: String) : PaymentEvent
    data class CVCChanged(val cvc: String) : PaymentEvent
    data class ExpirationDateChanged(val expirationDate: String) : PaymentEvent
}

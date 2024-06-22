package com.example.cheapac.presentation.feature.cart.checkout.shipping

sealed interface ShippingEvent {
    data class FirstNameChanged(val firstName: String) : ShippingEvent
    data class LastNameChanged(val lastName: String) : ShippingEvent
    data class PhoneNumberChanged(val phoneNumber: String) : ShippingEvent
    data class AddressChanged(val address: String) : ShippingEvent
    data class CountrySelected(val country: String) : ShippingEvent
}

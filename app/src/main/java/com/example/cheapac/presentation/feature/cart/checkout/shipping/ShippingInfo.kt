package com.example.cheapac.presentation.feature.cart.checkout.shipping

data class ShippingInfo(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val country: String = "",
) {
    val fullName
        get() = "$firstName $lastName"
}

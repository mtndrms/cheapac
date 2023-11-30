package com.example.cheapac.utils

fun Int.applyDiscount(discountRate: Int): Float {
    return this - (this.toFloat() / 100f) * discountRate
}
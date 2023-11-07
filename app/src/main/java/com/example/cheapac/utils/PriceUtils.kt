package com.example.cheapac.utils

fun Int.applyDiscount(discountRate: Int): Int {
    return this - (this / 100) * discountRate
}
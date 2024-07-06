package com.example.cheapac.utils

/**
 * Calculates the final price of a product after applying a discount.
 *
 * Calculation Example:
 * For an original price of 200 and a discount rate of 20%, the calculation is as follows:
 * Final Price = 200 - (200 / 100.0) * 20 = 160.0
 *
 * Usage Example:
 * ```
 * val originalPrice = 200
 * val discountRate = 20
 * val discountedPrice = originalPrice.applyDiscount(discountRate)
 * println(discountedPrice)  // Output: 160.0
 * ```
 *
 * @param discountRate The discount percentage to be applied to the original price. It must be a positive integer.
 * @return The final price after applying the discount, as a `Float`.
 */
fun Int.applyDiscount(discountRate: Int): Float {
    return this - (this.toFloat() / 100f) * discountRate
}
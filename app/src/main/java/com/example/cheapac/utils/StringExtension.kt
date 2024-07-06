package com.example.cheapac.utils

/**
 * Capitalizes the first character of the string.
 *
 * @return The string with the first character capitalized. If the string is empty, it returns the original string.
 */
fun String.capitalize(): String {
    return this.replaceFirstChar { it.uppercase() }
}

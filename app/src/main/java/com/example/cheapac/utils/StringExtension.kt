package com.example.cheapac.utils

fun String.capitalize(): String {
    return this.replaceFirstChar { it.uppercase() }
}

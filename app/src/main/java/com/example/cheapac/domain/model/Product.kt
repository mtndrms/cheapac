package com.example.cheapac.domain.model

data class Product(
    val id: Int,
    val title: String,
    val price: Int,
    val category: String,
    val description: String,
    val imageUrl: String
)
package com.example.cheapac.domain.model

data class Product(
    val id: Int,
    val title: String,
    val price: Int,
    val discountPercentage: Float,
    val rating: Float,
    val category: String,
    val description: String,
    val thumbnail: String,
    val images: List<String>
)
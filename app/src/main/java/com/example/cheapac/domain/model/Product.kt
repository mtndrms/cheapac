package com.example.cheapac.domain.model

data class Product(
    val id: Int,
    val brand: String,
    val title: String,
    val price: Int,
    val discountPercentage: Float,
    val rating: Float,
    val category: String,
    val stock: Int,
    val description: String,
    val thumbnail: String,
    val images: List<String>
)
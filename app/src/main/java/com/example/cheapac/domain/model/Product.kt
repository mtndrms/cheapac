package com.example.cheapac.domain.model

import com.example.cheapac.data.remote.dto.product.Review

data class Product(
    val id: Int,
    val brand: String,
    val title: String,
    val price: Int,
    val discountPercentage: Float,
    val reviews: List<Review>,
    val category: String,
    val stock: Int,
    val description: String,
    val thumbnail: String,
    val images: List<String>
)

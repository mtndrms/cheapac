package com.example.cheapac.data.mapper

import com.example.cheapac.data.remote.dto.product.ProductDto
import com.example.cheapac.domain.model.Product

fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        brand = "",
        title = title,
        price = price.toInt(),
        discountPercentage = discountPercentage,
        rating = rating,
        category = category,
        stock = stock,
        description = description,
        thumbnail = thumbnail,
        images = images
    )
}

fun List<ProductDto>.toProductList(): List<Product> {
    return this.map { productDto ->
        productDto.toProduct()
    }
}
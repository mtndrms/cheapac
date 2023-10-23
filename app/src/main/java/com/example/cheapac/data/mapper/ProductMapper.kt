package com.example.cheapac.data.mapper

import com.example.cheapac.data.remote.dto.product.ProductDto
import com.example.cheapac.domain.model.Product

fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        category = category,
        description = description,
        imageUrl = thumbnail
    )
}

fun List<ProductDto>.toProductList(): List<Product> {
    return this.map { productDto ->
        productDto.toProduct()
    }
}
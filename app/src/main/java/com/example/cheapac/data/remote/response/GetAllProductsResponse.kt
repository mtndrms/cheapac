package com.example.cheapac.data.remote.response

import com.example.cheapac.data.remote.dto.product.ProductDto

data class GetAllProductsResponse(
    val products: List<ProductDto>,
    val total: Int,
    val skip: Int,
    val limit: Int,
)

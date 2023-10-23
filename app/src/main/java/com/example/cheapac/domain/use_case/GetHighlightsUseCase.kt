package com.example.cheapac.domain.use_case

import com.example.cheapac.data.mapper.toProductList
import com.example.cheapac.data.repository.ProductRepository
import com.example.cheapac.domain.model.Product

class GetHighlightsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): List<Product> {
        val highlights = productRepository.getAll(limit = 5, skip = 0).products
        return highlights.toProductList()
    }
}
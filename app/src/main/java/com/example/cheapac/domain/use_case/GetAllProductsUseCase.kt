package com.example.cheapac.domain.use_case

import com.example.cheapac.data.mapper.toProductList
import com.example.cheapac.data.repository.ProductRepository
import com.example.cheapac.domain.model.Product

class GetAllProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): List<Product> {
        val products = productRepository.getAll().products
        return products.toProductList()
    }
}
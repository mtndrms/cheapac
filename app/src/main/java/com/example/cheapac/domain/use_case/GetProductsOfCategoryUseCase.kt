package com.example.cheapac.domain.use_case

import com.example.cheapac.data.mapper.toProductList
import com.example.cheapac.data.repository.ProductRepository
import com.example.cheapac.domain.model.Product
import com.example.cheapac.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsOfCategoryUseCase @Inject constructor(private val productRepository: ProductRepository) {
    operator fun invoke(category: String): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading())
            val products = productRepository.getProductsOfCategory(category = category).products.toProductList()
            emit(Resource.Success(data = products))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message.orEmpty()))
        }
    }
}
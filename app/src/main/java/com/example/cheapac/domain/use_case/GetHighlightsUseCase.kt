package com.example.cheapac.domain.use_case

import com.example.cheapac.data.mapper.toProductList
import com.example.cheapac.data.repository.ProductRepository
import com.example.cheapac.domain.model.Product
import com.example.cheapac.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHighlightsUseCase @Inject constructor(private val productRepository: ProductRepository) {
    operator fun invoke(): Flow<Resource<HashMap<Int, List<Product>>>> = flow {
        try {
            emit(Resource.Loading())
            val mainHighlights = productRepository.getAll(limit = 5, skip = 0).products.toProductList()
            val subHighlights = productRepository.getAll(limit = 5, skip = 5).products.toProductList()

            val highlights = HashMap<Int, List<Product>>()
            highlights[0] = mainHighlights
            highlights[1] = subHighlights

            emit(Resource.Success(data = highlights))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message ?: ""))
        }
    }
}
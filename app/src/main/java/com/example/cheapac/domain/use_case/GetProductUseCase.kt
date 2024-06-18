package com.example.cheapac.domain.use_case

import com.example.cheapac.data.mapper.toProduct
import com.example.cheapac.data.repository.ProductRepository
import com.example.cheapac.domain.model.Product
import com.example.cheapac.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val repository: ProductRepository) {
    operator fun invoke(id: Int): Flow<Resource<Product>> = flow {
        try {
            emit(Resource.Loading())
            val product = repository.getOneById(id = id).toProduct()
            emit(Resource.Success(data = product))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message.orEmpty()))
        }
    }
}
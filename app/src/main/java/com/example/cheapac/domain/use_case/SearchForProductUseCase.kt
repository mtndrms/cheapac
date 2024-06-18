package com.example.cheapac.domain.use_case

import com.example.cheapac.data.Resource
import com.example.cheapac.data.UiState
import com.example.cheapac.data.mapper.toProductList
import com.example.cheapac.data.repository.ProductRepository
import com.example.cheapac.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchForProductUseCase @Inject constructor(private val repository: ProductRepository) {
    operator fun invoke(query: String): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading())
            val searchResult = repository.searchForProduct(query).products.toProductList()
            emit(Resource.Success(data = searchResult))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message.orEmpty()))
        }
    }
}
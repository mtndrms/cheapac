package com.example.cheapac.domain.use_case

import com.example.cheapac.data.Resource
import com.example.cheapac.data.mapper.toCartItem
import com.example.cheapac.data.repository.CartRepository
import com.example.cheapac.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(private val cartRepository: CartRepository) {
    operator fun invoke(product: Product): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            cartRepository.insert(product = product.toCartItem())
            emit(Resource.Success(data = true))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message ?: ""))
        }
    }
}
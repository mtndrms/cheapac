package com.example.cheapac.domain.use_case

import com.example.cheapac.data.Resource
import com.example.cheapac.data.local.entity.CartItem
import com.example.cheapac.data.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private val cartRepository: CartRepository) {
    operator fun invoke(): Flow<Resource<List<CartItem>>> = flow {
        try {
            emit(Resource.Loading())
            val cartItems = cartRepository.getAll()
            emit(Resource.Success(data = cartItems))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message.orEmpty()))
        }
    }
}
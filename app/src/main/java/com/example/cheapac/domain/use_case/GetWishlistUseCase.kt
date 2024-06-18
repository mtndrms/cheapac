package com.example.cheapac.domain.use_case

import com.example.cheapac.data.Resource
import com.example.cheapac.data.local.entity.WishlistItem
import com.example.cheapac.data.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWishlistUseCase @Inject constructor(private val wishlistRepository: WishlistRepository) {
    operator fun invoke(): Flow<Resource<List<WishlistItem>>> = flow {
        try {
            emit(Resource.Loading())
            val items = wishlistRepository.getAll()
            emit(Resource.Success(data = items))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message.orEmpty()))
        }
    }
}
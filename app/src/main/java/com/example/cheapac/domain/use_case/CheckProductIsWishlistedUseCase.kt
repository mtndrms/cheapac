package com.example.cheapac.domain.use_case

import com.example.cheapac.data.Resource
import com.example.cheapac.data.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckProductIsWishlistedUseCase @Inject constructor(private val wishlistRepository: WishlistRepository) {
    operator fun invoke(id: Int): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val isExists = wishlistRepository.isExists(id = id)
            emit(Resource.Success(data = isExists))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message ?: ""))
        }
    }
}
package com.example.cheapac.domain.use_case

import com.example.cheapac.data.Resource
import com.example.cheapac.data.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckProductIsWishlistedUseCase @Inject constructor(private val wishlistRepository: WishlistRepository) {
    operator fun invoke(id: Int): Flow<Boolean> = flow {
        try {
            emit(false)
            val isExists = wishlistRepository.isExists(id = id)
            emit(isExists)
        } catch (exception: Exception) {
            emit(false)
        }
    }
}
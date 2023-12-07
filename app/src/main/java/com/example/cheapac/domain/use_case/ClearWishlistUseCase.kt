package com.example.cheapac.domain.use_case

import com.example.cheapac.data.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClearWishlistUseCase @Inject constructor(private val wishlistRepository: WishlistRepository) {
    operator fun invoke(): Flow<Boolean> = flow {
        try {
            val rowsEffected = wishlistRepository.clear()
            emit(rowsEffected > 0)
        } catch (exception: Exception) {
            emit(false)
        }
    }
}
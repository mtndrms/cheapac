package com.example.cheapac.domain.use_case

import com.example.cheapac.data.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveProductFromWishlist @Inject constructor(private val wishlistRepository: WishlistRepository) {
    operator fun invoke(id: Int): Flow<Boolean> = flow {
        val rowsEffected = wishlistRepository.deleteOneById(id = id)
        emit(rowsEffected > 0)
    }
}
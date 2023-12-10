package com.example.cheapac.domain.use_case

import com.example.cheapac.data.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWishlistByCategoryUseCase @Inject constructor(private val wishlistRepository: WishlistRepository) {
    operator fun invoke(category: String): Flow<List<Int>> = flow {
        try {
            val items = wishlistRepository.getByCategory(category = category)
            emit(items)
        } catch (exception: Exception) {
            emit(emptyList())
        }
    }
}
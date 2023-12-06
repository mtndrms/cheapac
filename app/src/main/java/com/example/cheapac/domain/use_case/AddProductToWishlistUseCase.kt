package com.example.cheapac.domain.use_case

import com.example.cheapac.data.Resource
import com.example.cheapac.data.local.entity.WishlistItem
import com.example.cheapac.data.repository.WishlistRepository
import com.example.cheapac.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddProductToWishlistUseCase @Inject constructor(private val wishlistRepository: WishlistRepository) {
    operator fun invoke(product: Product, note: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            wishlistRepository.insert(
                product = WishlistItem(
                    product.id,
                    product.title,
                    product.thumbnail,
                    note = note
                )
            )
            emit(Resource.Success(data = true))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message ?: ""))
        }
    }
}
package com.example.cheapac.domain.use_case

import com.example.cheapac.data.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(private val cartRepository: CartRepository) {
    operator fun invoke(): Flow<Boolean> = flow {
        try {
            val rowsEffected = cartRepository.clear()
            emit(rowsEffected > 0)
        } catch (exception: Exception) {
            emit(false)
        }
    }
}
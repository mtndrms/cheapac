package com.example.cheapac.domain.use_case

import com.example.cheapac.data.repository.RecentlyViewedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveRecentlyViewedProductUseCase @Inject constructor(private val recentlyViewedRepository: RecentlyViewedRepository) {
    operator fun invoke(id: Int): Flow<Boolean> = flow {
        try {
            val rowsEffected = recentlyViewedRepository.deleteOneById(id = id)
            emit(rowsEffected > 0)
        } catch (exception: Exception) {
            emit(false)
        }
    }
}
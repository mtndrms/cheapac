package com.example.cheapac.domain.use_case

import com.example.cheapac.data.repository.RecentlyViewedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClearRecentlyViewedItems @Inject constructor(private val recentlyViewedRepository: RecentlyViewedRepository) {
    operator fun invoke(): Flow<Boolean> = flow {
        try {
            val rowsEffected = recentlyViewedRepository.clear()
            emit(rowsEffected >= 0)
        } catch (exception: Exception) {
            emit(false)
        }
    }
}
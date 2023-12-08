package com.example.cheapac.domain.use_case

import com.example.cheapac.data.local.entity.RecentlyViewedItem
import com.example.cheapac.data.repository.RecentlyViewedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateRecentlyViewedProductRecordUseCase @Inject constructor(private val recentlyViewedRepository: RecentlyViewedRepository) {
    operator fun invoke(id: Int, title: String, thumbnailUrl: String): Flow<Boolean> = flow {
        try {
            recentlyViewedRepository.insert(
                product = RecentlyViewedItem(
                    id = id,
                    title = title,
                    thumbnailUrl = thumbnailUrl,
                )
            )
            emit(true)
        } catch (exception: Exception) {
            emit(false)
        }
    }
}
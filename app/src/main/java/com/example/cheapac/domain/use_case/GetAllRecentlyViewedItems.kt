package com.example.cheapac.domain.use_case

import com.example.cheapac.data.Resource
import com.example.cheapac.data.local.entity.RecentlyViewedItem
import com.example.cheapac.data.repository.RecentlyViewedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllRecentlyViewedItems @Inject constructor(private val recentlyViewedRepository: RecentlyViewedRepository) {
    operator fun invoke(): Flow<Resource<List<RecentlyViewedItem>>> = flow {
        try {
            emit(Resource.Loading())
            val items = recentlyViewedRepository.getAll()
            emit(Resource.Success(data = items))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message ?: ""))
        }
    }
}
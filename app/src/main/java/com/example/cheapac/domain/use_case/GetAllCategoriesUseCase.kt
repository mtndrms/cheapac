package com.example.cheapac.domain.use_case

import com.example.cheapac.data.Resource
import com.example.cheapac.data.mapper.stringtoCategory
import com.example.cheapac.data.repository.CategoryRepository
import com.example.cheapac.domain.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {
    operator fun invoke(): Flow<Resource<List<Category>>> = flow {
        try {
            emit(Resource.Loading())
            val categories = categoryRepository.getAll().map { stringtoCategory(it) }
            emit(Resource.Success(data = categories))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message ?: ""))
        }
    }
}
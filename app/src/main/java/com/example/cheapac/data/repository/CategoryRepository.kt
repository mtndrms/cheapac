package com.example.cheapac.data.repository

import com.example.cheapac.data.remote.RemoteDataSource
import com.example.cheapac.data.remote.dto.CategoryDto
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getAll(): List<CategoryDto> = remoteDataSource.getAllCategories()
}
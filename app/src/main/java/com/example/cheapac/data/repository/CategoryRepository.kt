package com.example.cheapac.data.repository

import com.example.cheapac.data.remote.RemoteDataSource
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getAll(): List<String> = remoteDataSource.getAllCategories()
}
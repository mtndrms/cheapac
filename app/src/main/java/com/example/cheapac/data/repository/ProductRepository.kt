package com.example.cheapac.data.repository

import com.example.cheapac.data.remote.data_source.ProductRemoteDataSource
import com.example.cheapac.data.remote.dto.GetAllProductsResponse
import com.example.cheapac.data.remote.dto.product.ProductDto
import javax.inject.Inject

class ProductRepository @Inject constructor(private val remoteDataSource: ProductRemoteDataSource) {
    suspend fun getAll(limit: Int? = null, skip: Int? = null): GetAllProductsResponse =
        remoteDataSource.getAll(limit, skip)

    suspend fun getOneById(id: Int): ProductDto = remoteDataSource.getOneById(id)
}
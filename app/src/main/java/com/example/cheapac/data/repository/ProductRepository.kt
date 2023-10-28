package com.example.cheapac.data.repository

import com.example.cheapac.data.remote.RemoteDataSource
import com.example.cheapac.data.remote.dto.GetAllProductsResponse
import com.example.cheapac.data.remote.dto.product.ProductDto
import javax.inject.Inject

class ProductRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getAll(limit: Int? = null, skip: Int? = null): GetAllProductsResponse =
        remoteDataSource.getAllProducts(limit, skip)

    suspend fun getOneById(id: Int): ProductDto = remoteDataSource.getOneProductById(id)
}
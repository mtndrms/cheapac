package com.example.cheapac.data.remote

import com.example.cheapac.data.remote.dto.GetAllProductsResponse
import com.example.cheapac.data.remote.dto.product.ProductDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: ProductApi
) {
    suspend fun getAllProducts(limit: Int? = null, skip: Int? = null): GetAllProductsResponse =
        withContext(Dispatchers.IO) {
            api.getAllProducts(limit, skip)
        }

    suspend fun getOneProductById(id: Int): ProductDto =
        withContext(Dispatchers.IO) {
            api.getOneProductById(id)
        }

    suspend fun getAllCategories(): List<String> =
        withContext(Dispatchers.IO) {
            api.getAllCategories()
        }
}
package com.example.cheapac.data.remote

import com.example.cheapac.data.remote.dto.CategoryDto
import com.example.cheapac.data.remote.dto.GetAllProductsResponse
import com.example.cheapac.data.remote.dto.product.ProductDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: ApiService
) {
    suspend fun getAllProducts(limit: Int? = null, skip: Int? = null): GetAllProductsResponse =
        withContext(Dispatchers.IO) {
            api.getAllProducts(limit, skip)
        }

    suspend fun getOneProductById(id: Int): ProductDto =
        withContext(Dispatchers.IO) {
            api.getOneProductById(id)
        }

    suspend fun getAllCategories(): List<CategoryDto> =
        withContext(Dispatchers.IO) {
            api.getAllCategories()
        }

    suspend fun getProductsOfCategory(
        category: String,
        limit: Int? = null,
        skip: Int? = null
    ): GetAllProductsResponse =
        withContext(Dispatchers.IO) {
            api.getProductsOfCategory(category = category, limit = limit, skip = skip)
        }
}
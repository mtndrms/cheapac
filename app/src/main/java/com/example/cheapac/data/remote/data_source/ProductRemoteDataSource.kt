package com.example.cheapac.data.remote.data_source

import com.example.cheapac.data.remote.dto.GetAllProductsResponse
import com.example.cheapac.data.remote.dto.product.ProductDto
import com.example.cheapac.data.remote.service.ProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val api: ProductApi
) {
    suspend fun getAll(limit: Int? = null, skip: Int? = null): GetAllProductsResponse =
        withContext(Dispatchers.IO) {
            api.getAll(limit, skip)
        }

    suspend fun getOneById(id: Int): ProductDto =
        withContext(Dispatchers.IO) {
            api.getOneById(id)
        }
}
package com.example.cheapac.data.remote.service

import com.example.cheapac.data.remote.dto.GetAllProductsResponse
import com.example.cheapac.data.remote.dto.product.ProductDto
import com.example.cheapac.utils.SortingType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val endpoint = "products"

interface ProductApi {
    @GET(value = endpoint)
    suspend fun getAll(
        @Query(value = "limit") limit: Int? = null,
        @Query(value = "skip") skip: Int? = null
    ): GetAllProductsResponse

    @GET(value = "$endpoint/{id}")
    suspend fun getOneById(@Path(value = "id") id: Int): ProductDto

    @GET(value = "$endpoint/categories")
    suspend fun getAllCategories(): List<String>
}
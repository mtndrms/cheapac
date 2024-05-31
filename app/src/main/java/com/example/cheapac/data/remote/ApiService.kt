package com.example.cheapac.data.remote

import com.example.cheapac.data.remote.dto.CategoryDto
import com.example.cheapac.data.remote.dto.GetAllProductsResponse
import com.example.cheapac.data.remote.dto.product.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val endpoint = "products"

interface ApiService {
    @GET(value = endpoint)
    suspend fun getAllProducts(
        @Query(value = "limit") limit: Int? = null,
        @Query(value = "skip") skip: Int? = null
    ): GetAllProductsResponse

    @GET(value = "$endpoint/{id}")
    suspend fun getOneProductById(@Path(value = "id") id: Int): ProductDto

    @GET(value = "$endpoint/categories")
    suspend fun getAllCategories(): List<CategoryDto>

    @GET(value = "$endpoint/category/{category}")
    suspend fun getProductsOfCategory(
        @Path(value = "category") category: String,
        @Query(value = "limit") limit: Int? = null,
        @Query(value = "skip") skip: Int? = null
    ): GetAllProductsResponse
}
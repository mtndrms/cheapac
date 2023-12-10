package com.example.cheapac.data.repository

import com.example.cheapac.data.local.LocalDataSource
import com.example.cheapac.data.local.entity.WishlistItem
import javax.inject.Inject

class WishlistRepository @Inject constructor(private val localDataSource: LocalDataSource) {
    suspend fun getAll(): List<WishlistItem> = localDataSource.getWishlist()
    suspend fun getByCategory(category: String): List<Int> = localDataSource.getWishlistByCategory(category = category)
    suspend fun isExists(id: Int): Boolean = localDataSource.isWishlistItemExists(id = id)
    suspend fun insert(product: WishlistItem) = localDataSource.addToWishlist(product = product)
    suspend fun delete(product: WishlistItem) = localDataSource.deleteWishlistedProduct(product = product)
    suspend fun deleteOneById(id: Int) = localDataSource.deleteWishlistedProductById(id = id)
    suspend fun clear() = localDataSource.clearWishlist()
}
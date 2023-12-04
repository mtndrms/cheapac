package com.example.cheapac.data.repository

import com.example.cheapac.data.local.LocalDataSource
import com.example.cheapac.data.local.entity.WishlistItem
import javax.inject.Inject

class WishlistRepository @Inject constructor(private val localDataSource: LocalDataSource) {
    suspend fun getAll(): List<WishlistItem> = localDataSource.getWishlist()
    suspend fun insert(product: WishlistItem) = localDataSource.addToWishlist(product = product)
    suspend fun delete(product: WishlistItem) = localDataSource.deleteWishlistedProduct(product = product)
    suspend fun clear() = localDataSource.clearWishlist()
}
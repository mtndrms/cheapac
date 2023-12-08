package com.example.cheapac.data.repository

import com.example.cheapac.data.local.LocalDataSource
import com.example.cheapac.data.local.entity.RecentlyViewedItem
import javax.inject.Inject

class RecentlyViewedRepository @Inject constructor(private val localDataSource: LocalDataSource) {
    suspend fun getAll(): List<RecentlyViewedItem> = localDataSource.getRecentlyViewedItems()
    suspend fun insert(product: RecentlyViewedItem) = localDataSource.insertRecentlyViewdItem(product = product)
    suspend fun delete(product: RecentlyViewedItem) = localDataSource.deleteRecentlyViewedItem(product = product)
    suspend fun deleteOneById(id: Int) = localDataSource.deleteRecentlyViewedItemById(id = id)
    suspend fun clear() = localDataSource.clearRecentlyViewedItems()
}
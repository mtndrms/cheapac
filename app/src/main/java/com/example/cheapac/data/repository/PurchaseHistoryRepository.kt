package com.example.cheapac.data.repository

import com.example.cheapac.data.local.LocalDataSource
import com.example.cheapac.data.local.entity.PurchaseHistoryItem
import javax.inject.Inject

class PurchaseHistoryRepository @Inject constructor(private val localDataSource: LocalDataSource) {
    suspend fun getAll(): List<PurchaseHistoryItem> = localDataSource.getPurchaseHistory()
    suspend fun insert(event: PurchaseHistoryItem) = localDataSource.createPurchaseHistoryEvent(event = event)
}
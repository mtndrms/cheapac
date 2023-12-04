package com.example.cheapac.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cheapac.data.local.Table
import com.example.cheapac.data.local.entity.PurchaseHistoryItem

interface PurchaseHistoryDao {
    @Query("SELECT * FROM ${Table.PURCHASE_HISTORY}")
    fun getAll(): List<PurchaseHistoryItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(historyEvent: PurchaseHistoryItem)
}
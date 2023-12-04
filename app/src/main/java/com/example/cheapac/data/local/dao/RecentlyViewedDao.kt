package com.example.cheapac.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cheapac.data.local.Table
import com.example.cheapac.data.local.entity.RecentlyViewedItem

@Dao
interface RecentlyViewedDao {
    @Query("SELECT * FROM ${Table.RECENTLY_VIEWED}")
    fun getAll(): List<RecentlyViewedItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(product: RecentlyViewedItem)

    @Delete
    fun deleteOne(product: RecentlyViewedItem)

    @Query("DELETE FROM ${Table.RECENTLY_VIEWED}")
    fun clear()
}
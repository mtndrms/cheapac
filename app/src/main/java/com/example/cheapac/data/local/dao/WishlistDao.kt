package com.example.cheapac.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cheapac.data.local.Table
import com.example.cheapac.data.local.entity.WishlistItem

@Dao
interface WishlistDao {
    @Query("SELECT * FROM ${Table.WISHLIST}")
    fun getAll(): List<WishlistItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOne(product: WishlistItem)

    @Delete
    fun deleteOne(product: WishlistItem)

    @Query("DELETE FROM ${Table.WISHLIST}")
    fun clear()
}
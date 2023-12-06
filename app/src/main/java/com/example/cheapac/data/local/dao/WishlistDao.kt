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

    @Query("SELECT EXISTS(SELECT * FROM ${Table.WISHLIST} WHERE id = :id)")
    fun isExists(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOne(product: WishlistItem)

    @Delete
    fun deleteOne(product: WishlistItem)

    @Query("DELETE FROM ${Table.WISHLIST} WHERE id = :id")
    fun deleteOneById(id: Int): Int

    @Query("DELETE FROM ${Table.WISHLIST}")
    fun clear()
}
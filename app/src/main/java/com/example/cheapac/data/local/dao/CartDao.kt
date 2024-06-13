package com.example.cheapac.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cheapac.data.local.Table
import com.example.cheapac.data.local.entity.CartItem

@Dao
interface CartDao {
    @Query("SELECT * FROM ${Table.CART}")
    suspend fun getAll(): List<CartItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOne(product: CartItem)

    @Delete
    suspend fun deleteOne(product: CartItem)

    @Query("DELETE FROM ${Table.CART}")
    suspend fun clear(): Int

    @Update(CartItem::class)
    suspend fun incrementQuantity(cartItem: CartItem)

    @Update(CartItem::class)
    suspend fun decrementQuantity(cartItem: CartItem)
}
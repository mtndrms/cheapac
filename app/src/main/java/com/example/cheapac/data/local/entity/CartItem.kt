package com.example.cheapac.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cheapac.data.local.Table
import com.example.cheapac.data.local.entity.superclass.Product

@Entity(tableName = Table.CART)
data class CartItem(
    @PrimaryKey override val id: Int,
    @ColumnInfo(name = "title") override val title: String,
    @ColumnInfo(name = "thumbnail_url") override val thumbnailUrl: String,
    @ColumnInfo(name = "quantity") val quantity: Int
): Product()

package com.example.cheapac.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cheapac.data.local.entity.superclass.Product
import java.util.Calendar
import java.util.Date

@Entity(tableName = "wishlist")
data class WishlistItem(
    @PrimaryKey override val id: Int,
    @ColumnInfo(name = "title") override val title: String,
    @ColumnInfo(name = "thumbnail_url") override val thumbnailUrl: String,
    @ColumnInfo(name = "created_at") val dateAdded: Date = Calendar.getInstance().time,
    @ColumnInfo(name = "note") val note: String
): Product()

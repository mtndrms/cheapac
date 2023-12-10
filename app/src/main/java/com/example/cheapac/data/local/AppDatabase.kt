package com.example.cheapac.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cheapac.data.local.dao.CartDao
import com.example.cheapac.data.local.dao.PurchaseHistoryDao
import com.example.cheapac.data.local.dao.RecentlyViewedDao
import com.example.cheapac.data.local.dao.WishlistDao
import com.example.cheapac.data.local.entity.CartItem
import com.example.cheapac.data.local.entity.PurchaseHistoryItem
import com.example.cheapac.data.local.entity.RecentlyViewedItem
import com.example.cheapac.data.local.entity.WishlistItem

@Database(
    entities = [
        CartItem::class,
        WishlistItem::class,
        RecentlyViewedItem::class,
        PurchaseHistoryItem::class
    ],
    version = 2
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun recentlyViewedDao(): RecentlyViewedDao
    abstract fun wishlistDao(): WishlistDao
    abstract fun purchaseHistoryDao(): PurchaseHistoryDao
}
package com.example.cheapac.data.local

import com.example.cheapac.data.local.entity.CartItem
import com.example.cheapac.data.local.entity.PurchaseHistoryItem
import com.example.cheapac.data.local.entity.RecentlyViewedItem
import com.example.cheapac.data.local.entity.WishlistItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val database: AppDatabase
) {
    suspend fun getCart() =
        withContext(Dispatchers.IO) {
            database.cartDao().getAll()
        }

    suspend fun addToCart(product: CartItem) =
        withContext(Dispatchers.IO) {
            database.cartDao().insertOne(product = product)
        }

    suspend fun deleteProductFromCart(product: CartItem) =
        withContext(Dispatchers.IO) {
            database.cartDao().deleteOne(product = product)
        }

    suspend fun clearCart() =
        withContext(Dispatchers.IO) {
            database.cartDao().clear()
        }

    suspend fun getRecentlyViewedItems() =
        withContext(Dispatchers.IO) {
            database.recentlyViewedDao().getAll()
        }

    suspend fun insertRecentlyViewdItem(product: RecentlyViewedItem) =
        withContext(Dispatchers.IO) {
            database.recentlyViewedDao().insertOne(product = product)
        }

    suspend fun deleteRecentlyViewedItem(product: RecentlyViewedItem) =
        withContext(Dispatchers.IO) {
            database.recentlyViewedDao().deleteOne(product = product)
        }

    suspend fun clearRecentlyViewedItems() =
        withContext(Dispatchers.IO) {
            database.recentlyViewedDao().clear()
        }

    suspend fun getWishlist() =
        withContext(Dispatchers.IO) {
            database.wishlistDao().getAll()
        }

    suspend fun isWishlistItemExists(id: Int) =
        withContext(Dispatchers.IO) {
            database.wishlistDao().isExists(id = id)
        }

    suspend fun addToWishlist(product: WishlistItem) =
        withContext(Dispatchers.IO) {
            database.wishlistDao().insertOne(product = product)
        }

    suspend fun deleteWishlistedProduct(product: WishlistItem) =
        withContext(Dispatchers.IO) {
            database.wishlistDao().deleteOne(product = product)
        }

    suspend fun deleteWishlistedProductById(id: Int) =
        withContext(Dispatchers.IO) {
            database.wishlistDao().deleteOneById(id = id)
        }

    suspend fun clearWishlist() =
        withContext(Dispatchers.IO) {
            database.wishlistDao().clear()
        }

    suspend fun getPurchaseHistory() =
        withContext(Dispatchers.IO) {
            database.purchaseHistoryDao().getAll()
        }

    suspend fun createPurchaseHistoryEvent(event: PurchaseHistoryItem) =
        withContext(Dispatchers.IO) {
            database.purchaseHistoryDao().insertOne(historyEvent = event)
        }
}
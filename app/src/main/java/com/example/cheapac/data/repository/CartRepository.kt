package com.example.cheapac.data.repository

import com.example.cheapac.data.local.LocalDataSource
import com.example.cheapac.data.local.entity.CartItem
import javax.inject.Inject

class CartRepository @Inject constructor(private val localDataSource: LocalDataSource) {
    suspend fun getAll(): List<CartItem> = localDataSource.getCart()
    suspend fun insert(product: CartItem) = localDataSource.addToCart(product = product)
    suspend fun delete(product: CartItem) = localDataSource.deleteProductFromCart(product = product)
    suspend fun clear() = localDataSource.clearCart()
}
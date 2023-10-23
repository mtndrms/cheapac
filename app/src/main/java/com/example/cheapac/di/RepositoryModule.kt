package com.example.cheapac.di

import com.example.cheapac.data.remote.data_source.ProductRemoteDataSource
import com.example.cheapac.data.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideProductRepository(productRemoteDataSource: ProductRemoteDataSource): ProductRepository {
        return ProductRepository(productRemoteDataSource)
    }
}
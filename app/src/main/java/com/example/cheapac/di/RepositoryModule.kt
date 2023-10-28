package com.example.cheapac.di

import com.example.cheapac.data.remote.RemoteDataSource
import com.example.cheapac.data.repository.CategoryRepository
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
    fun provideProductRepository(remoteDataSource: RemoteDataSource): ProductRepository {
        return ProductRepository(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(remoteDataSource: RemoteDataSource): CategoryRepository {
        return CategoryRepository(remoteDataSource)
    }
}
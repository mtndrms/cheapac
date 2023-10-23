package com.example.cheapac.di

import com.example.cheapac.data.remote.data_source.ProductRemoteDataSource
import com.example.cheapac.data.remote.service.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideProductRemoteDataSource(productApi: ProductApi): ProductRemoteDataSource {
        return ProductRemoteDataSource(productApi)
    }
}
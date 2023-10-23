package com.example.cheapac.di

import com.example.cheapac.data.repository.ProductRepository
import com.example.cheapac.domain.use_case.GetAllCategoriesUseCase
import com.example.cheapac.domain.use_case.GetAllProductsUseCase
import com.example.cheapac.domain.use_case.GetHighlightsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetAllProductsUseCase(productRepository: ProductRepository): GetAllProductsUseCase {
        return GetAllProductsUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideGetHighlightsUseCase(productRepository: ProductRepository): GetHighlightsUseCase {
        return GetHighlightsUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllCategoriesUseCase(productRepository: ProductRepository): GetAllCategoriesUseCase {
        return GetAllCategoriesUseCase(productRepository)
    }
}
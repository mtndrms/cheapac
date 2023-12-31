package com.example.cheapac.di

import android.content.Context
import androidx.room.Room
import com.example.cheapac.data.local.AppDatabase
import com.example.cheapac.data.local.Migration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = "cheapac-db"
            )
            .addMigrations(Migration.MIGRATION_1_2)
            .build()
    }
}
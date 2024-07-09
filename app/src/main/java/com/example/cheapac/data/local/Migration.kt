package com.example.cheapac.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// Object that holds migration definitions for the database
object Migration {
    // Define a migration from version 1 to version 2
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Execute SQL command to add a new column 'category' to the 'WISHLIST' table
            // The new column is of type TEXT, cannot be NULL, and has a default value of an empty string
            db.execSQL("ALTER TABLE ${Table.WISHLIST} ADD COLUMN category TEXT NOT NULL DEFAULT ``")
        }
    }
}

package com.example.cheapac.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE ${Table.WISHLIST} ADD COLUMN category TEXT NOT NULL DEFAULT ``")
        }
    }
}

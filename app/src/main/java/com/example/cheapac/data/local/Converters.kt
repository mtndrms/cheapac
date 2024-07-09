package com.example.cheapac.data.local

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type
import java.util.Date

// Class that provides type converters for Room database
class Converters {
    private val moshi: Moshi = Moshi.Builder().build()

    // Converter to convert a timestamp (Long) to a Date object
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    // Converter to convert a Date object to a timestamp (Long)
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    // Converter to convert a list of Integers to a JSON string
    @TypeConverter
    fun fromIntList(value: List<Int>?): String? {
        val type: Type = Types.newParameterizedType(List::class.java, Int::class.java)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(type)
        return adapter.toJson(value)
    }

    // Converter to convert a JSON string to a list of Integers
    @TypeConverter
    fun toIntList(value: String?): List<Int>? {
        val type: Type = Types.newParameterizedType(List::class.java, Int::class.java)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(type)
        return adapter.fromJson(value ?: "[]")
    }
}
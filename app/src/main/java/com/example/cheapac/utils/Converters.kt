package com.example.cheapac.utils

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type
import java.util.Date

class Converters {
    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromIntList(value: List<Int>?): String? {
        val type: Type = Types.newParameterizedType(List::class.java, Int::class.java)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun toIntList(value: String?): List<Int>? {
        val type: Type = Types.newParameterizedType(List::class.java, Int::class.java)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(type)
        return adapter.fromJson(value ?: "[]")
    }
}
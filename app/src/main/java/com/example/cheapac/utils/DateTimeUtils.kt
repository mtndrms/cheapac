package com.example.cheapac.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateTimeUtils {
    fun convertStringToDate(input: String, desiredFormat: String? = null): String {
        val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)
        inputFormat.timeZone = TimeZone.getTimeZone("GMT")

        val date: Date = inputFormat.parse(input)!!

        val outputFormat = desiredFormat?.let {
            SimpleDateFormat(desiredFormat, Locale.getDefault())
        } ?: run {
            SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        }

        return outputFormat.format(date)
    }
}

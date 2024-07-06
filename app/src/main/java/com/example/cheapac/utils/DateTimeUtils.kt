package com.example.cheapac.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Utility class for handling and formatting date and time operations.
 */
object DateTimeUtils {
    /**
     * Retrieves the current date and time formatted as a string.
     *
     * This function returns the current date and time using the format "yyyy-MM-dd HH:mm:ss".
     * It uses the system's default locale to format the date and time appropriately.
     *
     * Example:
     * ```
     * val currentDateTime = now()
     * println(currentDateTime) // Outputs something like: "2024-07-06 11:12:13"
     * ```
     *
     * @return The current date and time as a `String` in the format "yyyy-MM-dd HH:mm:ss".
     * @see DateTimeUtils for related utilities.
     */
    fun now(): String {
        val currentDate = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return formatter.format(currentDate)
    }

    /**
     * Formats a given date string into the desired format.
     *
     * This function takes a date string formatted as "EEE MMM dd HH:mm:ss zzz yyyy" (e.g., "Mon Jul 06 11:12:13 GMT 2024")
     * and converts it into a specified format. If no desired format is provided, it defaults to "dd/MM/yyyy HH:mm:ss".
     *
     * Example Usage:
     * ```
     * val inputDate = "Mon Jul 06 11:12:13 GMT 2024"
     * val formattedDate = format(inputDate) // Uses default format: "06/07/2024 11:12:13"
     * val customFormattedDate = format(inputDate, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") // Custom format: "2024-07-06T11:12:13.000Z"
     * println(formattedDate) // Outputs: "06/07/2024 11:12:13"
     * println(customFormattedDate) // Outputs: "2024-07-06T11:12:13.000Z"
     * ```
     *
     * @param input The date string to be formatted. It should be in the format "EEE MMM dd HH:mm:ss zzz yyyy" (e.g., "Mon Jul 06 11:12:13 GMT 2024").
     * @param desiredFormat The desired output format as a string. If not provided, defaults to "dd/MM/yyyy HH:mm:ss".
     * @return The formatted date as a `String`.
     * @see DateTimeUtils for related utilities.
     */
    fun format(input: String, desiredFormat: String? = null): String {
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

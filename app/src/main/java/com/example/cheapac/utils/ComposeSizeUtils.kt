package com.example.cheapac.utils

import android.content.Context
import android.util.TypedValue

/**
 * Converts pixels (px) to density-independent pixels (dp) using the device's screen density.
 *
 * @param context The context used to retrieve display metrics.
 * @param px The value in pixels to be converted.
 * @return The converted value in density-independent pixels (dp).
 */
fun pxToDp(context: Context, px: Int): Float {
    val density = context.resources.displayMetrics.density
    return px.toFloat() / density
}
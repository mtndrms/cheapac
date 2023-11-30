package com.example.cheapac.utils

import android.content.Context
import android.util.TypedValue

fun pxToDp(context: Context, px: Int): Float {
    val density = context.resources.displayMetrics.density
    return px.toFloat() / density
}
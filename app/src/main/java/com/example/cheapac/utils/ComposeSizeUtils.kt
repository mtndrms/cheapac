package com.example.cheapac.utils

import android.content.Context
import android.util.TypedValue

fun dpToPx(dp: Float, scale: Float): Int {
    return (dp * scale + 0.5f).toInt()
}

fun pxToDp(px: Int, scale: Float): Float {
    return px / scale
}

fun pxToDp(context: Context, px: Int): Float {
    val density = context.resources.displayMetrics.density
    return px.toFloat() / density
}
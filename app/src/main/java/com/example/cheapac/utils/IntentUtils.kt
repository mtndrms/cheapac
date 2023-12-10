package com.example.cheapac.utils

import android.content.Context
import android.content.Intent

fun handleShareProductClick(context: Context, title: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Check this out from Cheapac: lets_pretend_there_is_a_product_link_here")
        putExtra(Intent.EXTRA_TITLE, title)

        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}
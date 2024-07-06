package com.example.cheapac.utils

import android.content.Context
import android.content.Intent

/**
 * Launches an intent to share product details with other apps.
 *
 * Example Usage:
 * ```
 * // Assuming this is called from an Composable, Activity or Fragment:
 * handleShareProductClick(context, "Product Title")
 * // This will open a share dialog where the user can choose an app to share the product details.
 * ```
 *
 * @param context The `Context` in which the share intent should be launched. Typically, this would be an `Activity` or `Fragment`.
 * @param title The title of the product to be included in the share intent, displayed as the subject or title in the chosen app.
 */
fun handleShareProductClick(context: Context, title: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "Check this out from Cheapac: lets_pretend_there_is_a_product_link_here"
        )
        putExtra(Intent.EXTRA_TITLE, title)

        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}
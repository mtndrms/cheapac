package com.example.cheapac.presentation.component.top_bar

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class representing options for a top bar button.
 *
 * @property icon The icon of the button, represented as an ImageVector.
 * @property iconID The resource ID of the icon, if represented as a Drawable resource.
 * @property badgeLabel The label to be displayed as a badge on the button.
 * @property shouldShowBadge Boolean indicating if the badge should be shown.
 * @property onClick Lambda function to be executed when the button is clicked.
 * @property shouldHideButton Boolean indicating if the button should be hidden.
 *
 */
data class TopBarButtonOpts(
    val icon: ImageVector? = null,
    @DrawableRes val iconID: Int? = null,
    val badgeLabel: String? = null,
    val shouldShowBadge: Boolean = badgeLabel != null,
    val onClick: () -> Unit = {},
    val shouldHideButton: Boolean = false,
)

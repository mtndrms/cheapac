package com.example.cheapac.presentation.component.top_bar

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarButtonOpts(
    val icon: ImageVector? = null,
    @DrawableRes val iconID: Int? = null,
    val badgeLabel: String? = null,
    val shouldShowBadge: Boolean = badgeLabel != null,
    val onClick: () -> Unit = {},
    val shouldHideButton: Boolean = false,
)

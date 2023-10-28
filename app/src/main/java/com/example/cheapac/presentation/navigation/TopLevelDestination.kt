package com.example.cheapac.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cheapac.R
import com.example.cheapac.presentation.common.CheapacIcons

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextResId: Int,
    val titleTextResId: Int,
    val route: String
) {
    HOME(
        selectedIcon = CheapacIcons.Home,
        unselectedIcon = CheapacIcons.HomeOutlined,
        iconTextResId = R.string.home,
        titleTextResId = R.string.home,
        route = "home"
    ),
    PROFILE(
        selectedIcon = CheapacIcons.Profile,
        unselectedIcon = CheapacIcons.ProfileOutlined,
        iconTextResId = R.string.profile,
        titleTextResId = R.string.profile,
        route = "profile"
    ),
}
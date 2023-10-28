package com.example.cheapac.utils

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.cheapac.R
import com.example.cheapac.presentation.navigation.Destination
import com.example.cheapac.presentation.navigation.TopLevelDestination

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

fun NavDestination?.showTopBarOnThisDestinations() =
    when (this?.route) {
        TopLevelDestination.HOME.route -> true
        Destination.CATEGORIES.route -> true
        else -> false
    }

fun NavDestination?.hideBottomBarOnThisDestinations() =
    when (this?.route) {
        else -> false
    }

fun NavDestination?.getDestinationTitle() =
    when (this?.route) {
        TopLevelDestination.HOME.route -> R.string.app_name
        TopLevelDestination.PROFILE.route -> TopLevelDestination.PROFILE.titleTextResId
        Destination.CATEGORIES.route -> Destination.CATEGORIES.titleTextResId
        else -> R.string.app_name
    }
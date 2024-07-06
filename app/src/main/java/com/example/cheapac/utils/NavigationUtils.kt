package com.example.cheapac.utils

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.cheapac.R
import com.example.cheapac.presentation.navigation.Destination
import com.example.cheapac.presentation.navigation.TopLevelDestination

/**
 * Checks if a given top-level destination is within the navigation hierarchy of the current destination.
 *
 * @param destination The `TopLevelDestination` to check for in the navigation hierarchy.
 * @return `true` if the specified top-level destination is found in the hierarchy of the current destination, `false` otherwise.
 */
fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

/**
 * Determines whether the top bar should be shown for the current navigation destination.
 *
 * @return `true` if the top bar should be displayed for the current destination, `false` otherwise.
 */
fun NavDestination?.showTopBarOnThisDestinations() =
    when (this?.route) {
        TopLevelDestination.HOME.route -> true
        Destination.CATEGORIES.route -> true
        else -> false
    }

/**
 * Determines whether the bottom bar should be hidden for the current navigation destination.
 *
 * @return `true` if the bottom bar should be hidden for the current destination, `false` otherwise.
 */
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
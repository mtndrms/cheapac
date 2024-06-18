package com.example.cheapac.presentation.feature.recently_viewed

sealed interface RecentlyViewedEvent {
    data object InitialFetch : RecentlyViewedEvent
    data object Clear : RecentlyViewedEvent
}

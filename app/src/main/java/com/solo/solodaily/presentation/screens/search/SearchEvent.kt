package com.solo.solodaily.presentation.screens.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    data object SearchNews : SearchEvent()
}

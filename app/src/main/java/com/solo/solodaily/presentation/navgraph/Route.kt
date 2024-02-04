package com.solo.solodaily.presentation.navgraph

sealed class Route(
    val route: String,
) {
    data object OnboardingScreen : Route(route = "onboarding")
    data object HomeScreen : Route(route = "home")
    data object SearchScreen : Route(route = "search")
    data object BookmarkScreen : Route(route = "bookmark")
    data object DetailsScreen : Route(route = "details")
    data object AppStartNavigation : Route(route = "appStartNav")
    data object NewsNavigation : Route(route = "newsNav")
    data object NewsScreen : Route(route = "news")
}

package com.solo.solodaily.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.solo.solodaily.presentation.bookmark.BookmarkScreen
import com.solo.solodaily.presentation.bookmark.BookmarkViewModel
import com.solo.solodaily.presentation.home.HomeScreen
import com.solo.solodaily.presentation.home.HomeViewModel
import com.solo.solodaily.presentation.newsnavigator.NewsNavigator
import com.solo.solodaily.presentation.onboarding.OnboardingScreen
import com.solo.solodaily.presentation.onboarding.OnboardingViewModel
import com.solo.solodaily.presentation.search.SearchScreen
import com.solo.solodaily.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnboardingScreen.route,
        ) {
            composable(
                route = Route.OnboardingScreen.route,
            ) {
                val viewModel: OnboardingViewModel = hiltViewModel()

                OnboardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsScreen.route,
        ) {
            composable(
                route = Route.NewsScreen.route,
            ) {
                NewsNavigator()
            }

        }
    }
}

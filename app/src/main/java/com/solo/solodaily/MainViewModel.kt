package com.solo.solodaily

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.solodaily.domain.usecases.AppEntryUseCases
import com.solo.solodaily.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases,
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { startHomeScreen ->
            startDestination = if (startHomeScreen) {
                Route.NewsNavigation.route
            } else Route.AppStartNavigation.route

            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}

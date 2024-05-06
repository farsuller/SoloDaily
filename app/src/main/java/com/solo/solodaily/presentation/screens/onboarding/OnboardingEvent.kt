package com.solo.solodaily.presentation.screens.onboarding

sealed class OnboardingEvent {
    data object SaveAppEntry : OnboardingEvent()
}

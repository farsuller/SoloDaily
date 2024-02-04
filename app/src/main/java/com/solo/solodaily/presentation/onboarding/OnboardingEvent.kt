package com.solo.solodaily.presentation.onboarding

sealed class OnboardingEvent {
    data object SaveAppEntry : OnboardingEvent()
}

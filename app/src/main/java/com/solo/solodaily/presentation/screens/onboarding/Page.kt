package com.solo.solodaily.presentation.screens.onboarding

import androidx.annotation.DrawableRes
import com.solo.solodaily.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Stay Informed",
        description = "Your Daily Digest of Breaking News and Updates",
        image = R.drawable.onboarding1,
    ),
    Page(
        title = "Discover the World",
        description = "Explore Timely Stories from Across the Globe",
        image = R.drawable.onboarding2,
    ),
    Page(
        title = "News Tailored for You",
        description = "Customized Content to Suit Your Interests",
        image = R.drawable.onboarding3,
    ),
)

package com.solo.solodaily.presentation.details

sealed class DetailsEvent {

    data object SaveArticle : DetailsEvent()
}

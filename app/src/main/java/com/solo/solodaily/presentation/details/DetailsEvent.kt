package com.solo.solodaily.presentation.details

import com.solo.solodaily.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    data object RemoveSideEffect : DetailsEvent()
}

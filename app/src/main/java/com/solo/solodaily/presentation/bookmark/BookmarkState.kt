package com.solo.solodaily.presentation.bookmark

import com.solo.solodaily.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList(),
)

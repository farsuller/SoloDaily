package com.solo.solodaily.presentation.search

import androidx.paging.PagingData
import com.solo.solodaily.domain.model.Article
import kotlinx.coroutines.flow.Flow


data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)

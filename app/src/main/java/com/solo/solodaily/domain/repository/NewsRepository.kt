package com.solo.solodaily.domain.repository

import androidx.paging.PagingData
import com.solo.solodaily.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}

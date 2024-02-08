package com.solo.solodaily.domain.usecases.news

import androidx.paging.PagingData
import com.solo.solodaily.domain.model.Article
import com.solo.solodaily.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}

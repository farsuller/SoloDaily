package com.solo.solodaily.domain.usecases.news

import com.solo.solodaily.domain.model.Article
import com.solo.solodaily.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}

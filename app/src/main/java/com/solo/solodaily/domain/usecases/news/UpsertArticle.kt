package com.solo.solodaily.domain.usecases.news

import com.solo.solodaily.data.local.NewsDao
import com.solo.solodaily.domain.model.Article
import com.solo.solodaily.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article = article)
    }
}

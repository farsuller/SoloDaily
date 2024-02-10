package com.solo.solodaily.domain.usecases.news

import com.solo.solodaily.domain.model.Article
import com.solo.solodaily.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article = article)
    }
}

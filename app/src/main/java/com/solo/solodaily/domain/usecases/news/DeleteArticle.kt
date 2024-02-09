package com.solo.solodaily.domain.usecases.news

import com.solo.solodaily.data.local.NewsDao
import com.solo.solodaily.domain.model.Article

class DeleteArticle(
    private val newsDao: NewsDao,
) {
    suspend fun invoke(article: Article) {
        newsDao.delete(article)
    }
}

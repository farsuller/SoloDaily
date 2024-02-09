package com.solo.solodaily.domain.usecases.news

import com.solo.solodaily.data.local.NewsDao
import com.solo.solodaily.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao,
) {
    suspend fun invoke(article: Article) {
        newsDao.upsert(article)
    }
}

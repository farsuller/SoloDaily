package com.solo.solodaily.domain.usecases.news

import com.solo.solodaily.data.local.NewsDao
import com.solo.solodaily.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao,
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}

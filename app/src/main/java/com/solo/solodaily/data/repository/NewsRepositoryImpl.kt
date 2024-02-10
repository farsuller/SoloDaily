package com.solo.solodaily.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.solo.solodaily.data.local.NewsDao
import com.solo.solodaily.data.remote.NewsPagingSource
import com.solo.solodaily.data.remote.SearchNewsPagingSource
import com.solo.solodaily.data.remote.dto.NewsApi
import com.solo.solodaily.domain.model.Article
import com.solo.solodaily.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    val newsDao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ","),
                )
            },
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ","),
                )
            },
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article = article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article = article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
       return newsDao.getArticle(url = url)
    }
}

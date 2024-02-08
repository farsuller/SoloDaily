package com.solo.solodaily.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.solo.solodaily.data.remote.NewsPagingSource
import com.solo.solodaily.data.remote.dto.NewsApi
import com.solo.solodaily.domain.model.Article
import com.solo.solodaily.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
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
}

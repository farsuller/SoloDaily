package com.solo.solodaily.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.solo.solodaily.BuildConfig
import com.solo.solodaily.data.remote.dto.NewsApi
import com.solo.solodaily.domain.model.Article
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String,

) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(sources = sources, page = page, apiKey = BuildConfig.API_KEY)
            totalNewsCount += newsResponse.articles.size

            LoadResult.Page(
                data = newsResponse.articles.distinctBy { it.title },
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null,
            )
        } catch (e: IOException) {
            e.printStackTrace()
            LoadResult.Error(throwable = e,)
        } catch (httpException: HttpException) {
            return LoadResult.Error(httpException)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

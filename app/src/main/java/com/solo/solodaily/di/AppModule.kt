package com.solo.solodaily.di

import android.app.Application
import com.solo.solodaily.BuildConfig
import com.solo.solodaily.data.manager.LocalUserManagerImpl
import com.solo.solodaily.data.remote.dto.NewsApi
import com.solo.solodaily.data.repository.NewsRepositoryImpl
import com.solo.solodaily.domain.manager.LocalUserManager
import com.solo.solodaily.domain.repository.NewsRepository
import com.solo.solodaily.domain.usecases.appentry.AppEntryUseCases
import com.solo.solodaily.domain.usecases.appentry.ReadAppEntry
import com.solo.solodaily.domain.usecases.appentry.SaveAppEntry
import com.solo.solodaily.domain.usecases.news.GetNews
import com.solo.solodaily.domain.usecases.news.NewsUseCases
import com.solo.solodaily.domain.usecases.news.SearchNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager,
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager),
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
        )
    }
}

package com.solo.solodaily.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.solo.solodaily.BuildConfig
import com.solo.solodaily.data.local.NewsDao
import com.solo.solodaily.data.local.NewsDatabase
import com.solo.solodaily.data.local.NewsTypeConverter
import com.solo.solodaily.data.manager.LocalUserManagerImpl
import com.solo.solodaily.data.remote.dto.NewsApi
import com.solo.solodaily.data.repository.NewsRepositoryImpl
import com.solo.solodaily.domain.manager.LocalUserManager
import com.solo.solodaily.domain.repository.NewsRepository
import com.solo.solodaily.domain.usecases.appentry.AppEntryUseCases
import com.solo.solodaily.domain.usecases.appentry.ReadAppEntry
import com.solo.solodaily.domain.usecases.appentry.SaveAppEntry
import com.solo.solodaily.domain.usecases.news.DeleteArticle
import com.solo.solodaily.domain.usecases.news.GetNews
import com.solo.solodaily.domain.usecases.news.NewsUseCases
import com.solo.solodaily.domain.usecases.news.SearchNews
import com.solo.solodaily.domain.usecases.news.SelectArticle
import com.solo.solodaily.domain.usecases.news.SelectArticles
import com.solo.solodaily.domain.usecases.news.UpsertArticle
import com.solo.solodaily.utils.Constants.NEWS_DB_NAME
import com.solo.solodaily.utils.sharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    fun provideNewsApi(@ApplicationContext context: Context): NewsApi {

        val token by context.sharedPreferences(name = "token")

        val clientBuilder = OkHttpClient.Builder()

        clientBuilder
            .connectTimeout(30, TimeUnit.SECONDS)  // Maximum time to establish a connection
            .readTimeout(30, TimeUnit.SECONDS)     // Maximum time to wait for a server response
            .writeTimeout(30, TimeUnit.SECONDS)    // Maximum time to send data to the server

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            clientBuilder.addInterceptor(logging)
        }

        val tokenInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }

        clientBuilder.addInterceptor(tokenInterceptor)

        val client = clientBuilder.build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao,
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi, newsDao = newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository),
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application,
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DB_NAME,
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase,
    ): NewsDao = newsDatabase.newsDao
}

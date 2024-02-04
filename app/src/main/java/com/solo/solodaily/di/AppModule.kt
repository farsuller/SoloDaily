package com.solo.solodaily.di

import android.app.Application
import com.solo.solodaily.data.manager.LocalUserManagerImpl
import com.solo.solodaily.domain.manager.LocalUserManager
import com.solo.solodaily.domain.usecases.AppEntryUseCases
import com.solo.solodaily.domain.usecases.ReadAppEntry
import com.solo.solodaily.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}

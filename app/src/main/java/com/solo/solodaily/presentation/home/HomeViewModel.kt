package com.solo.solodaily.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.solo.solodaily.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    newsUseCases: NewsUseCases,
) : ViewModel() {

    val news = newsUseCases.getNews(
        sources = listOf("google-news", "wired", "the-verge"),
    ).cachedIn(viewModelScope)
}

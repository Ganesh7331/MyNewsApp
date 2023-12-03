package com.example.mynewsapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.mynewsapp.Api.NewsApi
import com.example.mynewsapp.paging.NewsPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsApi: NewsApi
): ViewModel() {


    val list= Pager(PagingConfig(pageSize = 10)){
        NewsPaging(newsApi)

    }.liveData.cachedIn(viewModelScope)

}
package com.example.mynewsapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mynewsapp.Api.NewsApi
import com.example.mynewsapp.dataClass.Article
import com.example.mynewsapp.dataClass.Constants

class NewsPaging(val newsApi: NewsApi): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            val anchorPage=state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page=params.key?:1

        return try{
            val data=newsApi.getNews("us",page, Constants.api_key)
            Log.e("HIGANESHNEW", data.body()?.articles.toString() )

            LoadResult.Page(
                data=data.body()?.articles!!,
                prevKey = if(page==1) null else page-1,
                nextKey = if(data.body()?.articles?.isEmpty()!!) null else page+1
            )

        }catch(e:Exception){
            LoadResult.Error(e)
        }

    }
}
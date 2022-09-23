package com.example.chillmax.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.chillmax.data.remote.ChillMaxApi
import com.example.chillmax.domain.models.UpcomingMovies
import javax.inject.Inject

class UpcomingMoviesSource @Inject constructor(
    private val chillMaxApi: ChillMaxApi
): PagingSource<Int, UpcomingMovies>() {
    override fun getRefreshKey(state: PagingState<Int, UpcomingMovies>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingMovies> {
        return try {
            val apiResponse = chillMaxApi.getUpcomingMovies()
            val upcomingMovies = apiResponse.upcomingMovies
            if (upcomingMovies.isNotEmpty()) {
                LoadResult.Page(
                    data = upcomingMovies,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
package com.example.chillmax.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.chillmax.data.remote.ChillMaxApi
import com.example.chillmax.domain.models.TVPopular
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TVPopularSource @Inject constructor(
    private val chillMaxApi: ChillMaxApi
): PagingSource<Int, TVPopular>() {
    override fun getRefreshKey(state: PagingState<Int, TVPopular>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVPopular> {
        return try {
            val nextPage = params.key ?: 1
            val tvPopular = chillMaxApi.getTVPopular(nextPage)
            LoadResult.Page(
                data = tvPopular.searches,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (tvPopular.searches.isEmpty()) null else tvPopular.page + 1
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}
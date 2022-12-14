package com.example.chillmax.domain.use_cases

import androidx.paging.PagingData
import com.example.chillmax.data.repository.Repository
import com.example.chillmax.domain.models.TVAiringToday
import com.example.chillmax.domain.models.TopRatedMovies
import kotlinx.coroutines.flow.Flow

class GetTVAiringTodayUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<TVAiringToday>>{
        return repository.getTVAiringToday()
    }
}


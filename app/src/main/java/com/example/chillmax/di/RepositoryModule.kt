package com.example.chillmax.di

import com.example.chillmax.data.repository.Repository
import com.example.chillmax.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesUseCases(repository: Repository): UseCases{
        return UseCases(
            addToMyListUseCase = AddToMyListUseCase(repository),
            deleteAllContentFromMyListUseCase = DeleteAllContentFromMyListUseCase(repository),
            deleteOneFromMyListUseCase = DeleteOneFromMyListUseCase(repository),
            getMovieCreditsUseCase = GetMovieCreditsUseCase(repository),
            getMovieGenresUseCase = GetMovieGenresUseCase(repository),
            getMyListUseCase = GetMyListUseCase(repository),
            getPopularMoviesUseCase = GetPopularMoviesUseCase(repository),
            getSelectedFromMyListUseCase = GetSelectedFromMyListUseCase(repository),
            getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(repository),
            getTVAiringTodayUseCase = GetTVAiringTodayUseCase(repository),
            getTVCreditsUseCase = GetTVCreditsUseCase(repository),
            getTVGenresUseCase = GetTVGenresUseCase(repository),
            getTVPopularUseCase = GetTVPopularUseCase(repository),
            getUpcomingMoviesUseCase = GetUpcomingMoviesUseCase(repository),
            multiSearchUseCase = MultiSearchUseCase(repository),
            tvTopRatedUseCase = TVTopRatedUseCase(repository)
        )
    }
}
package com.example.lloydstask.di

import com.example.lloydstask.data.repository.MoviesRepository
import com.example.lloydstask.domain.usecases.MovieDetailsUseCase
import com.example.lloydstask.domain.usecases.MovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun providesMovieListUseCase(
        repository: MoviesRepository
    ): MovieListUseCase = MovieListUseCase(repository)

    @Singleton
    @Provides
    fun providesMovieDetailsUseCase(
        repository: MoviesRepository
    ): MovieDetailsUseCase = MovieDetailsUseCase(repository)
}
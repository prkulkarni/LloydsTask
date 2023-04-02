package com.example.lloydstask.di

import com.example.lloydstask.data.datasource.remotedatasource.MoviesRemoteDataSource
import com.example.lloydstask.data.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesMovieRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource
    ): MoviesRepository = MoviesRepository(moviesRemoteDataSource)
}
package com.example.lloydstask.di

import com.example.lloydstask.data.datasource.remotedatasource.MoviesRemoteDataSource
import com.example.lloydstask.data.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(
        apiService: ApiService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): MoviesRemoteDataSource = MoviesRemoteDataSource(apiService, ioDispatcher)
}
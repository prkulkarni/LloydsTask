package com.example.lloydstask.data.datasource

import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun getMovieList(): Flow<Result<MovieListDomainModel?>>
    suspend fun getMovieDetails(id: String): Flow<Result<MovieDetailsDomainModel?>>
}
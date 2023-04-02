package com.example.lloydstask.domain.usecases

import com.example.lloydstask.data.constants.Constants.Companion.DEFAULT_ERROR_MESSAGE
import com.example.lloydstask.data.model.MovieListModel
import com.example.lloydstask.data.repository.MoviesRepository
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieListUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(): Flow<Result<MovieListModel?>> {
        return moviesRepository.getMovieList().map {
            if(it is Result.Success) {
                Result.Success(it.data?.toMovieListModel())
            } else {
                Result.Error(it.message ?: DEFAULT_ERROR_MESSAGE)
            }
        }
    }
}
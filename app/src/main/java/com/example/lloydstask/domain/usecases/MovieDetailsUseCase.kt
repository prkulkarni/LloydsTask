package com.example.lloydstask.domain.usecases

import com.example.lloydstask.data.constants.Constants.Companion.DEFAULT_ERROR_MESSAGE
import com.example.lloydstask.data.model.MovieDetailsModel
import com.example.lloydstask.data.repository.MoviesRepository
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(id: String): Flow<Result<MovieDetailsModel?>> {
        return moviesRepository.getMovieDetails(id).map {
            if (it is Result.Success) {
                Result.Success(it.data?.toMovieDetailsModel())
            } else {
                Result.Error(it.message ?: DEFAULT_ERROR_MESSAGE)
            }
        }
    }
}
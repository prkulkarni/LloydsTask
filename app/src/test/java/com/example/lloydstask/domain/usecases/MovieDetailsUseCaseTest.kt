package com.example.lloydstask.domain.usecases

import com.example.lloydstask.BaseTest
import com.example.lloydstask.data.repository.MoviesRepository
import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsUseCaseTest : BaseTest() {

    private val repository: MoviesRepository = mockk()
    private val movieDetailsDomainModelResult: Flow<Result<MovieDetailsDomainModel?>> = mockk()
    private val movieDetailsDomainModel: MovieDetailsDomainModel = mockk()

    private lateinit var movieDetailsUseCase: MovieDetailsUseCase

    override fun setUp() {
        super.setUp()
        movieDetailsUseCase = MovieDetailsUseCase(repository)
    }

    @Test
    fun getMovieDetails_onSuccess_returnsMovieList() = runTest {
        coEvery { repository.getMovieDetails(MOVIE_ID) } returns movieDetailsDomainModelResult
        coEvery { repository.getMovieDetails(MOVIE_ID) } returns flow {
            emit(Result.Success(movieDetailsDomainModel))
        }
        movieDetailsUseCase(MOVIE_ID)
        advanceUntilIdle()
        coVerify { repository.getMovieDetails(MOVIE_ID) }
        repository.getMovieDetails(MOVIE_ID).collect {
            assertTrue(it is Result.Success)
            assertTrue(it.data == movieDetailsDomainModel)
        }
    }

    @Test
    fun getMovieDetails_onError_returnsErrorMessage() = runTest {
        coEvery { repository.getMovieDetails(MOVIE_ID) } returns movieDetailsDomainModelResult
        coEvery { repository.getMovieDetails(MOVIE_ID) } returns flow {
            emit(Result.Error(DEFAULT_ERROR_MESSAGE))
        }
        movieDetailsUseCase(MOVIE_ID)
        advanceUntilIdle()
        coVerify { repository.getMovieDetails(MOVIE_ID) }
        repository.getMovieDetails(MOVIE_ID).collect {
            assertTrue(it is Result.Error)
            assertTrue(it.message == DEFAULT_ERROR_MESSAGE)
        }
    }
}
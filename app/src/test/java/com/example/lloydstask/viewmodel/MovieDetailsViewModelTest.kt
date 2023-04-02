package com.example.lloydstask.viewmodel

import com.example.lloydstask.BaseTest
import com.example.lloydstask.data.model.MovieDetailsModel
import com.example.lloydstask.domain.usecases.MovieDetailsUseCase
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest : BaseTest() {
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    private val movieDetailsUseCase: MovieDetailsUseCase = mockk()
    private val movieDetailsModelResult: Flow<Result<MovieDetailsModel?>> = mockk()
    private val movieDetailsModel: MovieDetailsModel = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        movieDetailsViewModel = MovieDetailsViewModel(movieDetailsUseCase, testDispatcher)
    }

    @Test
    fun fetchMovieDetails_returnsSuccess() = runTest {
        coEvery { movieDetailsUseCase(MOVIE_ID) } returns movieDetailsModelResult
        coEvery { movieDetailsUseCase(MOVIE_ID) } returns flow {
            emit(Result.Success(movieDetailsModel))
        }
        movieDetailsViewModel.fetchMovieDetails(MOVIE_ID)
        advanceUntilIdle()
        coVerify { movieDetailsUseCase(MOVIE_ID) }
        assertTrue(movieDetailsViewModel.movieDetailsState.value is Result.Success)
    }

    @Test
    fun fetchMovieDetails_returnsError() = runTest {
        coEvery { movieDetailsUseCase(MOVIE_ID) } returns movieDetailsModelResult
        coEvery { movieDetailsUseCase(MOVIE_ID) } returns flow {
            emit(Result.Error(DEFAULT_ERROR_MESSAGE))
        }
        movieDetailsViewModel.fetchMovieDetails(MOVIE_ID)
        advanceUntilIdle()
        coVerify { movieDetailsUseCase(MOVIE_ID) }
        assertTrue(movieDetailsViewModel.movieDetailsState.value.message == DEFAULT_ERROR_MESSAGE)
    }
}
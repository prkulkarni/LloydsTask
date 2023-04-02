package com.example.lloydstask.viewmodel

import com.example.lloydstask.BaseTest
import com.example.lloydstask.data.model.MovieListModel
import com.example.lloydstask.domain.usecases.MovieListUseCase
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
class MovieListViewModelTest : BaseTest() {
    private lateinit var movieListViewModel: MovieListViewModel

    private val movieListUseCase: MovieListUseCase = mockk()
    private val movieListModelResult: Flow<Result<MovieListModel?>> = mockk()
    private val movieListModel: MovieListModel = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        movieListViewModel = MovieListViewModel(movieListUseCase, testDispatcher)
    }

    @Test
    fun fetchMovieList_returnsSuccess() = runTest {
        coEvery { movieListUseCase() } returns movieListModelResult
        coEvery { movieListUseCase() } returns flow {
            emit(Result.Success(movieListModel))
        }
        movieListViewModel.fetchMovieList()
        advanceUntilIdle()
        coVerify { movieListUseCase() }
        assertTrue(movieListViewModel.movieListState.value is Result.Success)
    }

    @Test
    fun fetchMovieList_returnsError() = runTest {
        coEvery { movieListUseCase() } returns movieListModelResult
        coEvery { movieListUseCase() } returns flow {
            emit(Result.Error(DEFAULT_ERROR_MESSAGE))
        }
        movieListViewModel.fetchMovieList()
        advanceUntilIdle()
        coVerify { movieListUseCase() }
        assertTrue(movieListViewModel.movieListState.value.message == DEFAULT_ERROR_MESSAGE)
    }
}
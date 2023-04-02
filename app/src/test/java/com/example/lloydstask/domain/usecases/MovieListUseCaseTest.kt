package com.example.lloydstask.domain.usecases

import com.example.lloydstask.BaseTest
import com.example.lloydstask.data.repository.MoviesRepository
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieListUseCaseTest : BaseTest() {

    private val repository: MoviesRepository = mockk()
    private val movieListDomainModelResult: Flow<Result<MovieListDomainModel?>> = mockk()
    private val movieListDomainModel: MovieListDomainModel = mockk()

    private lateinit var movieListUseCase: MovieListUseCase

    override fun setUp() {
        super.setUp()
        movieListUseCase = MovieListUseCase(repository)
    }

    @Test
    fun getMovieList_onSuccess_returnsMovieList() = runTest {
        coEvery { repository.getMovieList() } returns movieListDomainModelResult
        coEvery { repository.getMovieList() } returns flow {
            emit(Result.Success(movieListDomainModel))
        }
        movieListUseCase()
        advanceUntilIdle()
        coVerify { repository.getMovieList() }
        repository.getMovieList().collect {
            assertTrue(it is Result.Success)
            assertTrue(it.data == movieListDomainModel)
        }
    }

    @Test
    fun getMovieList_onError_returnsErrorMessage() = runTest {
        coEvery { repository.getMovieList() } returns movieListDomainModelResult
        coEvery { repository.getMovieList() } returns flow {
            emit(Result.Error(DEFAULT_ERROR_MESSAGE))
        }
        movieListUseCase()
        advanceUntilIdle()
        coVerify { repository.getMovieList() }
        repository.getMovieList().collect {
            assertTrue(it is Result.Error)
            assertTrue(it.message == DEFAULT_ERROR_MESSAGE)
        }
    }
}
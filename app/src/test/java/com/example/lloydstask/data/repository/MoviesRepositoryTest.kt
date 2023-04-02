package com.example.lloydstask.data.datasource.remotedatasource.repository

import com.example.lloydstask.BaseTest
import com.example.lloydstask.data.datasource.remotedatasource.MoviesRemoteDataSource
import com.example.lloydstask.data.repository.MoviesRepository
import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesRepositoryTest: BaseTest() {

    private val remoteDataSource: MoviesRemoteDataSource = mockk()
    private val movieListDomainModelResult: Flow<Result<MovieListDomainModel?>> = mockk()
    private val movieDetailsDomainModelResult: Flow<Result<MovieDetailsDomainModel?>> = mockk()

    private lateinit var moviesRepository: MoviesRepository

    override fun setUp() {
        super.setUp()
        moviesRepository = MoviesRepository(remoteDataSource)
    }

    @Test
    fun getMovieList_returnMovieList() = runTest {
        coEvery { remoteDataSource.getMovieList() } returns movieListDomainModelResult

        moviesRepository.getMovieList()
        advanceUntilIdle()

        coVerify { remoteDataSource.getMovieList() }
    }

    @Test
    fun getMovieDetails_returnMovieDetails() = runTest {
        coEvery { remoteDataSource.getMovieDetails(MOVIE_ID) } returns movieDetailsDomainModelResult

        moviesRepository.getMovieDetails(MOVIE_ID)
        advanceUntilIdle()

        coVerify { remoteDataSource.getMovieDetails(MOVIE_ID) }
    }
}
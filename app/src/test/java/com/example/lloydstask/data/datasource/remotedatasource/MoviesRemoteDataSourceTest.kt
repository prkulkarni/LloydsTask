package com.example.lloydstask.data.datasource.remotedatasource

import com.example.lloydstask.BaseTest
import com.example.lloydstask.BuildConfig
import com.example.lloydstask.data.datasource.remotedatasource.MoviesRemoteDataSource
import com.example.lloydstask.data.model.MovieDetailsResponse
import com.example.lloydstask.data.model.MovieListResponse
import com.example.lloydstask.data.services.ApiService
import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MoviesRemoteDataSourceTest : BaseTest() {

    private val apiService: ApiService = mockk()
    private val movieListApiResponse: Response<MovieListResponse> = mockk()
    private val movieListResponse: MovieListResponse = mockk()
    private val movieLisDomainModel: MovieListDomainModel = mockk()
    private val movieDetailsApiResponse: Response<MovieDetailsResponse> = mockk()
    private val movieDetailsResponse: MovieDetailsResponse = mockk()
    private val movieDetailsDomainModel: MovieDetailsDomainModel = mockk()

    private lateinit var remoteDataSource: MoviesRemoteDataSource

    @Before
    override fun setUp() {
        super.setUp()
        remoteDataSource = MoviesRemoteDataSource(apiService, testDispatcher)
    }

    @Test
    fun getMovieList_returnsSuccess() = runTest {
        every { movieListResponse.toMovieListDomainModel() } returns movieLisDomainModel
        every { movieListApiResponse.body() } returns movieListResponse
        every { movieListApiResponse.isSuccessful } returns true
        coEvery {
            apiService.getMoviesList(
                MOVIE_LANGUAGE,
                BuildConfig.API_KEY
            )
        } returns movieListApiResponse

        val movieListDetails = remoteDataSource.getMovieList()
        var result: Result<MovieListDomainModel?>? = null

        movieListDetails.collect {
            result = it
        }

        advanceUntilIdle()

        coVerify { apiService.getMoviesList(MOVIE_LANGUAGE, BuildConfig.API_KEY) }
        assert(result is Result.Success)
    }

    @Test
    fun getMovieList_returnsError() = runTest {
        every { movieListApiResponse.message() } returns DEFAULT_ERROR_MESSAGE
        every { movieListApiResponse.isSuccessful } returns false
        coEvery {
            apiService.getMoviesList(
                MOVIE_LANGUAGE,
                BuildConfig.API_KEY
            )
        } returns movieListApiResponse

        val movieListDetails = remoteDataSource.getMovieList()
        var result: Result<MovieListDomainModel?>? = null

        movieListDetails.collect {
            result = it
        }

        advanceUntilIdle()

        coVerify { apiService.getMoviesList(MOVIE_LANGUAGE, BuildConfig.API_KEY) }
        assert(result is Result.Error)
    }

    @Test
    fun getMovieDetails_returnsSuccess() = runTest {
        every { movieDetailsResponse.toMovieDetailsDomainModel() } returns movieDetailsDomainModel
        every { movieDetailsApiResponse.body() } returns movieDetailsResponse
        every { movieDetailsApiResponse.isSuccessful } returns true
        coEvery {
            apiService.getMovieDetails(
                MOVIE_LANGUAGE,
                BuildConfig.API_KEY,
                MOVIE_ID
            )
        } returns movieDetailsApiResponse

        val movieDetails = remoteDataSource.getMovieDetails(MOVIE_ID)
        var result: Result<MovieDetailsDomainModel?>? = null

        movieDetails.collect {
            result = it
        }

        advanceUntilIdle()

        coVerify { apiService.getMovieDetails(MOVIE_LANGUAGE, BuildConfig.API_KEY, MOVIE_ID) }
        assert(result is Result.Success)
    }

    @Test
    fun getMovieDetails_returnsError() = runTest {
        every { movieDetailsApiResponse.message() } returns DEFAULT_ERROR_MESSAGE
        every { movieDetailsApiResponse.isSuccessful } returns false
        coEvery {
            apiService.getMovieDetails(
                MOVIE_LANGUAGE,
                BuildConfig.API_KEY,
                MOVIE_ID
            )
        } returns movieDetailsApiResponse

        val movieDetails = remoteDataSource.getMovieDetails(MOVIE_ID)
        var result: Result<MovieDetailsDomainModel?>? = null

        movieDetails.collect {
            result = it
        }

        advanceUntilIdle()

        coVerify { apiService.getMovieDetails(MOVIE_LANGUAGE, BuildConfig.API_KEY, MOVIE_ID) }
        assert(result is Result.Error)
    }
}
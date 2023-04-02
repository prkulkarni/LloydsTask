package com.example.lloydstask.data.services

import com.example.lloydstask.data.model.MovieDetailsResponse
import com.example.lloydstask.data.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{lang}/API/ComingSoon/{apiKey}")
    suspend fun getMoviesList(
        @Path("lang") lang: String,
        @Path("apiKey") apiKey: String,
    ): Response<MovieListResponse>

    @GET("{lang}/API/Title/{apiKey}/{id}")
    suspend fun getMovieDetails(
        @Path("lang") lang: String,
        @Path("apiKey") apiKey: String,
        @Path("id") id: String,
    ): Response<MovieDetailsResponse>
}
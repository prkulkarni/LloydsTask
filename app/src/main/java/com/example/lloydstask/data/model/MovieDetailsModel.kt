package com.example.lloydstask.data.model

/**
 * Possible duplicate of MovieDetailsDomainModel class,
 * but this is te case of fake duplication. As the project evolve
 * both the classes contain different data.
 * */
data class MovieDetailsModel(
    val imageUrl: String,
    val fullTitle: String,
    val plot: String?
)

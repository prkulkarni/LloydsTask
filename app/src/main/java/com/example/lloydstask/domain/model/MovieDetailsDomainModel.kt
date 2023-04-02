package com.example.lloydstask.domain.model

import com.example.lloydstask.data.model.MovieDetailsModel

data class MovieDetailsDomainModel(
    val imageUrl: String,
    val fullTitle: String,
    val plot: String?
) {
    fun toMovieDetailsModel(): MovieDetailsModel {
        return MovieDetailsModel(imageUrl, fullTitle, plot)
    }
}

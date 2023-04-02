package com.example.lloydstask.data.model

import com.example.lloydstask.domain.model.MovieDomainItem
import com.example.lloydstask.domain.model.MovieListDomainModel

data class MovieListResponse(
    val items: List<MovieItemResponse>?,
    val errorMessage: String?
) {
    fun toMovieListDomainModel(): MovieListDomainModel {
        val movieDomainItemList = mutableListOf<MovieDomainItem>()

        items?.forEach {
            movieDomainItemList.add(it.toMovieDomainItem())
        }
        return MovieListDomainModel(movieDomainItemList, errorMessage)

    }
}

data class MovieItemResponse(
    val id: String,
    val title: String,
    val fullTitle: String,
    val year: String,
    val image: String,
    val releaseState: String,
    val runtimeMins: String?,
    val runtimeStr: String?,
    val plot: String?,
    val contentRating: String?,
    val imDbRating: String?,
    val imDbRatingCount: String?,
    val metacriticRating: String?,
    val genres: String,
    val genreList: List<KeyValueItem>,
    val directors: String?,
    val directorList: List<StarItem>,
    val stars: String,
    val starList: List<StarItem>
) {
    fun toMovieDomainItem(): MovieDomainItem {
        return MovieDomainItem(id, title, image)
    }
}

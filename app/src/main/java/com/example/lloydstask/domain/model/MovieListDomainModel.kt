package com.example.lloydstask.domain.model

import com.example.lloydstask.data.model.MovieItem
import com.example.lloydstask.data.model.MovieListModel

data class MovieListDomainModel(
    val movieDomainItemList: List<MovieDomainItem>,
    val errorMessage: String?
) {
    fun toMovieListModel(): MovieListModel {
        val movieItemList = mutableListOf<MovieItem>()

        movieDomainItemList.forEach {
            movieItemList.add(it.toMovieItem())
        }
        return MovieListModel(movieItemList, errorMessage)
    }
}

data class MovieDomainItem(
    val id: String,
    val title: String,
    val imageUrl: String
) {
    fun toMovieItem(): MovieItem {
        return MovieItem(id, title, imageUrl)
    }
}

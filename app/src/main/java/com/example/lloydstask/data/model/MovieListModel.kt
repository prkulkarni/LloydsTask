package com.example.lloydstask.data.model

/**
 * Possible duplicate of MovieListDomainModel class,
 * but this is te case of fake duplication. As the project evolve
 * both the classes contain different data.
 * */
data class MovieListModel(
    val movieItemList: List<MovieItem>,
    val errorMessage: String?
)

data class MovieItem(
    val id: String,
    val title: String,
    val imageUrl: String
)

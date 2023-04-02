package com.example.lloydstask.data.model

data class KeyValueItem(
    val key: String?,
    val value: String?
)

data class StarItem(
    val id: String?,
    val name: String?
)

data class ActorItem(
    val id: String?,
    val image: String?,
    val name: String?,
    val asCharacter: String?
)

data class BoxOfficeItem(
    val budget: String?,
    val openingWeekendUSA: String?,
    val grossUSA: String?,
    val cumulativeWorldwideGross: String?,
)

data class SimilarItem(
    val id: String,
    val title: String,
    val image: String,
    val imDbRating: String
)
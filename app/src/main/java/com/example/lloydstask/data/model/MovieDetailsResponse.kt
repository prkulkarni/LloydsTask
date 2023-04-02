package com.example.lloydstask.data.model

import com.example.lloydstask.domain.model.MovieDetailsDomainModel

data class MovieDetailsResponse(
    val id: String,
    val title: String,
    val originalTitle: String?,
    val fullTitle: String,
    val type: String?,
    val year: String,
    val image: String,
    val releaseDate: String,
    val runtimeMins: String?,
    val runtimeStr: String?,
    val plot: String?,
    val plotLocal: String?,
    val plotLocalIsRtl: Boolean,
    val awards: String?,
    val directors: String?,
    val directorList: List<StarItem>,
    val writers: String?,
    val writerList: List<StarItem>,
    val stars: String,
    val starList: List<StarItem>,
    val actorList: List<ActorItem>,
    val fullCast: String?,
    val genres: String,
    val genreList: List<KeyValueItem>,
    val companies: String?,
    val companyList: List<StarItem>,
    val countries: String?,
    val countryList: List<KeyValueItem>,
    val languages: String?,
    val languageList: List<KeyValueItem>,
    val contentRating: String?,
    val imDbRating: String?,
    val imDbRatingVotes: String?,
    val metacriticRating: String?,
    val ratings: String?,
    val wikipedia: String?,
    val posters: String?,
    val images: String?,
    val trailer: String?,
    val boxOffice: BoxOfficeItem,
    val tagline: String?,
    val keywords: String,
    val keywordList: List<String>,
    val similars: List<SimilarItem>,
    val tvSeriesInfo: String?,
    val tvEpisodeInfo: String?,
    val errorMessage: String?
) {
    fun toMovieDetailsDomainModel(): MovieDetailsDomainModel {
        return MovieDetailsDomainModel(image, fullTitle, plot)
    }
}

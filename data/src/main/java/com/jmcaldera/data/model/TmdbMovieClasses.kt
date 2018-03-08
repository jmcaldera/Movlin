package com.jmcaldera.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by jmcaldera on 08-03-18.
 */
data class TmdbNowPlaying(
        @Expose @SerializedName("results") val movies: List<TmdbMovie>,    // results
        @Expose val page: Int,
        @Expose @SerializedName("total_results") val totalResults: Int,
        @Expose val dates: TmdbDates,
        @Expose @SerializedName("total_pages") val totalPages: Int
)

data class TmdbTopRated(
        @Expose val page: Int,
        @Expose @SerializedName("total_results") val totalResults: Int,
        @Expose @SerializedName("total_pages") val totalPages: Int,
        @Expose @SerializedName("results") val movies: List<TmdbMovie>
)

data class TmdbUpcoming(
        @Expose @SerializedName("results") val movies: List<TmdbMovie>,    // results
        @Expose val page: Int,
        @Expose @SerializedName("total_results") val totalResults: Int,
        @Expose val dates: TmdbDates,
        @Expose @SerializedName("total_pages") val totalPages: Int
)

data class TmdbPopular(
        @Expose val page: Int,
        @Expose @SerializedName("total_results") val totalResults: Int,
        @Expose @SerializedName("total_pages") val totalPages: Int,
        @Expose @SerializedName("results") val movies: List<TmdbMovie>
)

data class TmdbMovie(
        @Expose @SerializedName("vote_count") val voteCount: Int,
        @Expose val id: Int,
        @Expose val video: Boolean,
        @Expose @SerializedName("vote_average") val voteAverage: Double,
        @Expose val title: String,
        @Expose val popularity: Double,
        @Expose @SerializedName("poster_path") val posterPath: String,
        @Expose @SerializedName("original_language") val originalLanguage: String,
        @Expose @SerializedName("original_title") val originalTitle: String,
        @Expose @SerializedName("genre_ids") val genreIds: List<Int>,
        @Expose @SerializedName("backdrop_path") val backdropPath: String,
        @Expose val adult: Boolean,
        @Expose val overview: String,
        @Expose @SerializedName("release_date") val releaseDate: String
)

data class TmdbDates(val maximum: String, val minimum: String)
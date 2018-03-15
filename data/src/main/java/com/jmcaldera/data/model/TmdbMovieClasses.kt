package com.jmcaldera.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by jmcaldera on 08-03-18.
 */
data class TmdbNowPlayingUpcoming(
        @Expose @SerializedName("results") val movies: List<TmdbMovie>,    // results
        @Expose val page: Int,
        @Expose @SerializedName("total_results") val totalResults: Int,
        @Expose val dates: TmdbDates,
        @Expose @SerializedName("total_pages") val totalPages: Int
)

data class TmdbTopRatedPopular(
        @Expose val page: Int,
        @Expose @SerializedName("total_results") val totalResults: Int,
        @Expose @SerializedName("total_pages") val totalPages: Int,
        @Expose @SerializedName("results") val movies: List<TmdbMovie>
)

//data class TmdbUpcoming(
//        @Expose @SerializedName("results") val movies: List<TmdbMovie>,    // results
//        @Expose val page: Int,
//        @Expose @SerializedName("total_results") val totalResults: Int,
//        @Expose val dates: TmdbDates,
//        @Expose @SerializedName("total_pages") val totalPages: Int
//)
//
//data class TmdbPopular(
//        @Expose val page: Int,
//        @Expose @SerializedName("total_results") val totalResults: Int,
//        @Expose @SerializedName("total_pages") val totalPages: Int,
//        @Expose @SerializedName("results") val movies: List<TmdbMovie>
//)

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

data class TmdbMovieDetails(
        @Expose @SerializedName("vote_count") val voteCount: Int,
        @Expose val budge: Long,
        @Expose val id: Int,
        @Expose val video: Boolean,
        @Expose @SerializedName("vote_average") val voteAverage: Double,
        @Expose val title: String,
        @Expose val popularity: Double,
        @Expose @SerializedName("poster_path") val posterPath: String,
        @Expose @SerializedName("original_language") val originalLanguage: String,
        @Expose @SerializedName("original_title") val originalTitle: String,
        @Expose @SerializedName("genres") val genres: List<TmdbGenre>,
        @Expose @SerializedName("backdrop_path") val backdropPath: String,
        @Expose val adult: Boolean,
        @Expose val overview: String,
        @Expose @SerializedName("release_date") val releaseDate: String,
        @Expose val runtime: Int,
        @Expose val revenue: Long,
        @Expose val images: TmdbImages
)

data class TmdbGenre(@Expose val id: Int, @Expose val name: String)

data class TmdbImage(@Expose @SerializedName("aspect_ratio") val aspectRatio: Double,
                     @Expose @SerializedName("file_path") val filePath: String)

data class TmdbImages(@Expose val backdrops: List<TmdbImage>, @Expose val posters: List<TmdbImage>)
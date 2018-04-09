package com.jmcaldera.domain.model

/**
 * Created by jmcaldera on 07-03-18.
 */
data class Movie(
        val voteCount: Int,
        val id: Int,
        val video: Boolean,
        val voteAverage: Double,
        val title: String,
        val popularity: Double,
        val posterPath: String,
        val originalLanguage: String,
        val originalTitle: String,
        val genreIds: List<Int>,
        val backdropPath: String? = null,
        val adult: Boolean,
        val overview: String,
        val releaseDate: String
)

data class MovieDetails(
        val voteCount: Int,
        val budget: Long,
        val id: Int,
        val video: Boolean,
        val voteAverage: Double,
        val title: String,
        val popularity: Double,
        val posterPath: String,
        val originalLanguage: String,
        val originalTitle: String,
        val genres: List<Genre>,
        val backdropPath: String? = null,
        val adult: Boolean,
        val overview: String,
        val releaseDate: String,
        val runtime: Int,
        val revenue: Long,
        val images: Images

)

data class Genre(val id: Int, val name: String)

data class MovieImage(val aspectRatio: Double, val filePath: String)

data class Images(val backdrops: List<MovieImage>, val posters: List<MovieImage>)

data class CastMember(val id: Int, val name: String, val character: String, val profilePath: String?)

data class CrewMember(val id: Int, val name: String, val job: String, val department: String, val profilePath: String?)

data class Credits(val id: Int, val cast: List<CastMember>, val crew: List<CrewMember>)

data class Person(
        val id: Int,
        val name: String,
        val birthday: String,
        val deathday: String? = null,
        val placeOfBirth: String,
        val biography: String,
        val alsoKnownAs: List<String>,
        val profilePath: String?
)
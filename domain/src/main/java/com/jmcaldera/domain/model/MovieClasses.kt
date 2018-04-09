package com.jmcaldera.domain.model

/**
 * Created by jmcaldera on 07-03-18.
 */
data class Movie(
        val id: Int,
        val voteCount: Int,
        val video: Boolean,
        val voteAverage: Double,
        val title: String,
        val popularity: Double,
        val posterPath: String,
        val originalLanguage: String,
        val originalTitle: String,
        val adult: Boolean,
        val overview: String,
        val releaseDate: String,
        val backdropPath: String? = null,
        val details: MovieDetails? = null
)

data class MovieDetails(
        val overview: String,
        val budget: Long,
        val genres: List<Genre>,
        val runtime: Int,
        val revenue: Long,
        val backdropPath: String? = null,
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
        val placeOfBirth: String? = null,
        val biography: String? = null,
        val alsoKnownAs: List<String>,
        val profilePath: String?
)
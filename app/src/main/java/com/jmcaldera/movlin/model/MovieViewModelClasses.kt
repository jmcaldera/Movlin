package com.jmcaldera.movlin.model

/**
 * Created by jmcaldera on 08-03-18.
 */
data class MovieViewModel(
        val id: Int,
        val title: String,
        val posterPath: String,
        val releaseDate: String,
        val voteAverage: Double,
        val voteCount: Int,
        val details: MovieDetailsViewModel? = null
)

data class MovieDetailsViewModel(
        val overview: String,
        val budget: Long,
        val genres: List<GenreViewModel>,
        val runtime: Int,
        val revenue: Long,
        val backdropPath: String? = null,
        val images: ImagesViewModel
)

data class GenreViewModel(val id: Int, val name: String)

data class ImageViewModel(val filePath: String)

data class ImagesViewModel(val backdrops: List<ImageViewModel>, val posters: List<ImageViewModel>)

data class CastMemberViewModel(val id: Int, val name: String, val character: String,
                               val profilePath: String?)

data class CrewMemberViewModel(val id: Int, val name: String, val job: String,
                               val department: String, val profilePath: String?)

data class CreditsViewModel(val id: Int, val cast: List<CastMemberViewModel>,
                            val crew: List<CrewMemberViewModel>)

data class PersonViewModel(
        val id: Int,
        val name: String,
        val birthday: String,
        val deathday: String? = null,
        val placeOfBirth: String? = null,
        val biography: String? = null,
        val alsoKnownAs: List<String>,
        val profilePath: String?
)
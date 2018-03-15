package com.jmcaldera.movlin.model

/**
 * Created by jmcaldera on 08-03-18.
 */
data class MovieViewModel(val id: Int, val title: String, val posterPath: String)

data class MovieDetailsViewModel(val id: Int,
                                 val voteCount: Int,
                                 val budget: Long,
                                 val voteAverage: Double,
                                 val title: String,
                                 val posterPath: String,
                                 val genres: List<GenreViewModel>,
                                 val backdropPath: String? = null,
                                 val overview: String,
                                 val releaseDate: String,
                                 val runtime: Int,
                                 val revenue: Long,
                                 val images: ImagesViewModel)

data class GenreViewModel(val id: Int, val name: String)

data class ImageViewModel(val filePath: String)

data class ImagesViewModel(val backdrops: List<ImageViewModel>, val posters: List<ImageViewModel>)

data class CastMemberViewModel(val id: Int, val name: String, val character: String,
                               val profilePath: String?)

data class CrewMemberViewModel(val id: Int, val name: String, val job: String,
                               val department: String, val profilePath: String?)

data class CreditsViewModel(val id: Int, val cast: List<CastMemberViewModel>,
                            val crew: List<CrewMemberViewModel>)
package com.jmcaldera.movlin.model

import com.jmcaldera.domain.model.Genre

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
                                 val revenue: Long)

data class GenreViewModel(val id: Int, val name: String)
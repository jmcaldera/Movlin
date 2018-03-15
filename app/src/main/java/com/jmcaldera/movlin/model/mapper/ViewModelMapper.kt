package com.jmcaldera.movlin.model.mapper

import com.jmcaldera.domain.model.Genre
import com.jmcaldera.domain.model.MovieDetails
import com.jmcaldera.movlin.model.GenreViewModel
import com.jmcaldera.movlin.model.MovieDetailsViewModel

/**
 * Created by jmcaldera on 14-03-18.
 */
fun convertMovieDetailsFromDomain(movie: MovieDetails) : MovieDetailsViewModel = with(movie) {
    MovieDetailsViewModel(id, voteCount, budget, voteAverage, title, posterPath, convertGenreFromDomain(genres),
            backdropPath, overview, releaseDate, runtime, revenue)
}

fun convertGenreFromDomain(genres: List<Genre>): List<GenreViewModel> =
        genres.map { genre -> GenreViewModel(genre.id, genre.name) }

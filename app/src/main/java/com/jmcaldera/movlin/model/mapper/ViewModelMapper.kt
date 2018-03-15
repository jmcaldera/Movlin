package com.jmcaldera.movlin.model.mapper

import com.jmcaldera.domain.model.Genre
import com.jmcaldera.domain.model.Images
import com.jmcaldera.domain.model.MovieDetails
import com.jmcaldera.movlin.model.*

/**
 * Created by jmcaldera on 14-03-18.
 */
fun convertMovieDetailsFromDomain(movie: MovieDetails) : MovieDetailsViewModel = with(movie) {
    MovieDetailsViewModel(id, voteCount, budget, voteAverage, title, posterPath, convertGenreFromDomain(genres),
            backdropPath, overview, releaseDate, runtime, revenue, convertImagesFromDomain(images))
}

fun convertGenreFromDomain(genres: List<Genre>): List<GenreViewModel> =
        genres.map { genre -> GenreViewModel(genre.id, genre.name) }

private fun convertImagesFromDomain(images: Images): ImagesViewModel = with(images) {
    ImagesViewModel(backdrops.map { image -> ImageViewModel(image.filePath) },
            posters.map { image -> ImageViewModel(image.filePath) })
}

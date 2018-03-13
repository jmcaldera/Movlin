package com.jmcaldera.data.mapper

import com.jmcaldera.data.model.*
import com.jmcaldera.domain.model.*

/**
 * Created by jmcaldera on 08-03-18.
 */
fun convertToDomain(nowPlaying: TmdbNowPlayingUpcoming,
                    moviesMapper: (List<TmdbMovie>) -> List<Movie> = ::defaultMoviesMapper,
                    datesMapper: (TmdbDates) -> Dates = ::defaultDatesMapper): NowPlayingUpcoming = with(nowPlaying) {
    NowPlayingUpcoming(moviesMapper(movies), page, totalResults, datesMapper(dates), totalPages)
}

fun convertToDomain(topRated: TmdbTopRatedPopular,
                    moviesMapper: (List<TmdbMovie>) -> List<Movie> = ::defaultMoviesMapper): TopRatedPopular =
        with(topRated) { TopRatedPopular(page, totalResults, totalPages, moviesMapper(movies)) }

fun defaultMoviesMapper(movies: List<TmdbMovie>): List<Movie> {
    return movies.map {
        Movie(it.voteCount, it.id, it.video, it.voteAverage, it.title, it.popularity, it.posterPath,
                it.originalTitle, it.originalTitle, it.genreIds, it.backdropPath, it.adult, it.overview, it.releaseDate)
    }
}

fun defaultDatesMapper(dates: TmdbDates): Dates = dates.let { Dates(it.maximum, it.minimum) }

fun convertMovieDetailsToDomain(movie: TmdbMovieDetails): MovieDetails {
    return movie.let {
        MovieDetails(it.voteCount, it.budge, it.id, it.video, it.voteAverage, it.title, it.popularity, it.posterPath,
                it.originalTitle, it.originalTitle, genresMapper(it.genres), it.backdropPath, it.adult,
                it.overview, it.releaseDate, it.runtime, it.revenue)
    }
}

fun genresMapper(genres: List<TmdbGenre>) : List<Genre> {
    return genres.map { Genre(it.id, it.name) }
}
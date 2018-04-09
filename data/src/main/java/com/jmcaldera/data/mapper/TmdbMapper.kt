package com.jmcaldera.data.mapper

import com.jmcaldera.data.model.*
import com.jmcaldera.domain.model.*

/**
 * Created by jmcaldera on 08-03-18.
 */
fun convertToDomain(nowPlaying: TmdbNowPlayingUpcoming,
                    moviesMapper: (List<TmdbMovie>) -> List<Movie> = ::defaultMoviesMapper): List<Movie> = with(nowPlaying) {
    moviesMapper(movies)
}

fun convertToDomain(topRated: TmdbTopRatedPopular,
                    moviesMapper: (List<TmdbMovie>) -> List<Movie> = ::defaultMoviesMapper): List<Movie> =
        with(topRated) { moviesMapper(movies) }

fun defaultMoviesMapper(movies: List<TmdbMovie>): List<Movie> {
    return movies.map {
        Movie(it.id, it.voteCount, it.video, it.voteAverage, it.title, it.popularity, it.posterPath,
                it.originalTitle, it.originalTitle, it.adult, it.overview, it.releaseDate, it.backdropPath)
    }
}

fun convertMovieDetailsToDomain(movie: TmdbMovieDetails): Movie {
    return movie.let {
        val details = MovieDetails(it.overview, it.budget, genresMapper(it.genres), it.runtime,
                it.revenue, it.backdropPath, imagesMapper(it.images))
        Movie(it.id, it.voteCount, it.video, it.voteAverage, it.title, it.popularity, it.posterPath,
                it.originalTitle, it.originalTitle, it.adult, it.overview, it.releaseDate, it.backdropPath, details)
    }
}

private fun genresMapper(genres: List<TmdbGenre>) : List<Genre> {
    return genres.map { Genre(it.id, it.name) }
}

private fun imagesMapper(images: TmdbImages): Images = with(images) {
    Images(backdrops.map { image -> MovieImage(image.aspectRatio, image.filePath) },
            posters.map { image -> MovieImage(image.aspectRatio, image.filePath) })
}

fun convertCreditsToDomain(credits: TmdbCredits): Credits = with(credits) {
    Credits(id, cast.map { member -> CastMember(member.id, member.name, member.character, member.profilePath) },
            crew.map { member -> CrewMember(member.id, member.name, member.job,
                    member.department, member.profilePath) })
}

fun convertPersonToDomain(person: TmdbPerson): Person = with(person) {
    Person(id, name, birthday, deathday, placeOfBirth, biography, alsoKnownAs, profilePath)
}

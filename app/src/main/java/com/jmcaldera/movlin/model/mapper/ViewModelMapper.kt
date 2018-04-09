package com.jmcaldera.movlin.model.mapper

import com.jmcaldera.domain.model.*
import com.jmcaldera.movlin.model.*

/**
 * Created by jmcaldera on 14-03-18.
 */
fun convertMovieDetailsFromDomain(movie: Movie) : MovieViewModel {

    val details = with(movie.details!!) {
        MovieDetailsViewModel(overview, budget, convertGenreFromDomain(genres),
                runtime, revenue, backdropPath, convertImagesFromDomain(images))
    }

    return MovieViewModel(movie.id, movie.title, movie.posterPath, movie.releaseDate, movie.voteAverage,
            movie.voteCount, details)

}

fun convertGenreFromDomain(genres: List<Genre>): List<GenreViewModel> =
        genres.map { genre -> GenreViewModel(genre.id, genre.name) }

private fun convertImagesFromDomain(images: Images): ImagesViewModel = with(images) {
    ImagesViewModel(backdrops.map { image -> ImageViewModel(image.filePath) },
            posters.map { image -> ImageViewModel(image.filePath) })
}

fun convertCreditsFromDomain(credits: Credits) : CreditsViewModel = with(credits) {
    CreditsViewModel(id, cast.map { member -> CastMemberViewModel(member.id, member.name,
            member.character, member.profilePath) },
            crew.map { member -> CrewMemberViewModel(member.id, member.name, member.job,
                    member.department, member.profilePath) })
}

inline fun convertPersonFromDomain(person: Person): PersonViewModel = with(person) {
    PersonViewModel(id, name, birthday, deathday, placeOfBirth, biography, alsoKnownAs, profilePath)
}

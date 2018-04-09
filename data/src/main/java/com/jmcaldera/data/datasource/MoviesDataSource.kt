package com.jmcaldera.data.datasource

import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.*

/**
 * Created by jmcaldera on 08-03-18.
 */
interface MoviesDataSource {

    suspend fun requestNowPlayingMovies(): IOResult<List<Movie>, MovieError>

    suspend fun requestTopRatedMovies(): IOResult<List<Movie>, MovieError>

    suspend fun requestPopularMovies(): IOResult<List<Movie>, MovieError>

    suspend fun requestUpcomingMovies(): IOResult<List<Movie>, MovieError>

    suspend fun requestMovieDetails(movieId: Int): IOResult<MovieDetails, MovieError>

    suspend fun requestMovieCredits(movieId: Int): IOResult<Credits, MovieError>

    suspend fun requestPersonDetails(personId: Int): IOResult<Person, MovieError>
}
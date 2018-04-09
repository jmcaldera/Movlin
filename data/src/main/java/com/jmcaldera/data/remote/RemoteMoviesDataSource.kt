package com.jmcaldera.data.remote

import com.jmcaldera.data.datasource.MoviesDataSource
import com.jmcaldera.data.extension.orElse
import com.jmcaldera.data.extension.transformResult
import com.jmcaldera.data.mapper.convertCreditsToDomain
import com.jmcaldera.data.mapper.convertMovieDetailsToDomain
import com.jmcaldera.data.mapper.convertPersonToDomain
import com.jmcaldera.data.mapper.convertToDomain
import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.*

/**
 * Created by jmcaldera on 08-03-18.
 */
class RemoteMoviesDataSource(private val service: TmdbService) : MoviesDataSource {

    override suspend fun requestNowPlayingMovies(): IOResult<List<Movie>, MovieError> {
        return service.getNowPlayingMovies().transformResult {
            convertToDomain(this)
        }.orElse { NotFoundError() }
    }

    override suspend fun requestTopRatedMovies(): IOResult<List<Movie>, MovieError> {
        return service.getTopRatedMovies().transformResult {
            convertToDomain(this)
        }.orElse { NotFoundError() }
    }

    override suspend fun requestPopularMovies(): IOResult<List<Movie>, MovieError> {
        return service.getPopularMovies().transformResult {
            convertToDomain(this)
        }.orElse { NotFoundError() }
    }

    override suspend fun requestUpcomingMovies(): IOResult<List<Movie>, MovieError> {
        return service.getUpcomingMovies().transformResult {
            convertToDomain(this)
        }.orElse { NotFoundError() }
    }

    override suspend fun requestMovieDetails(movieId: Int): IOResult<MovieDetails, MovieError> {
        return service.getMovieDetails(movieId).transformResult {
            convertMovieDetailsToDomain(this)
        }.orElse { NotFoundError() }
    }

    override suspend fun requestMovieCredits(movieId: Int): IOResult<Credits, MovieError> {
        return service.getMovieCredits(movieId).transformResult {
            convertCreditsToDomain(this)
        }.orElse { NotFoundError() }
    }

    override suspend fun requestPersonDetails(personId: Int): IOResult<Person, MovieError> {
        return service.getPersonDetails(personId).transformResult {
            convertPersonToDomain(this)
        }.orElse { NotFoundError() }
    }
}
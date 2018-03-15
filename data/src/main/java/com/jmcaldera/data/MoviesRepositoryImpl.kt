package com.jmcaldera.data

import com.jmcaldera.data.datasource.MoviesDataSource
import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.*
import com.jmcaldera.domain.repository.MoviesRepository

/**
 * Created by jmcaldera on 21-02-18.
 */
class MoviesRepositoryImpl(private val remoteDataSource: MoviesDataSource) : MoviesRepository {

    override suspend fun getNowPlayingMovies(): IOResult<NowPlayingUpcoming, MovieError> {
        return remoteDataSource.requestNowPlayingMovies()
    }

    override suspend fun getTopRatedMovies(): IOResult<TopRatedPopular, MovieError> {
        return remoteDataSource.requestTopRatedMovies()
    }

    override suspend fun getPopularMovies(): IOResult<TopRatedPopular, MovieError> {
        return remoteDataSource.requestPopularMovies()
    }

    override suspend fun getUpcomingMovies(): IOResult<NowPlayingUpcoming, MovieError> {
        return remoteDataSource.requestUpcomingMovies()
    }

    override suspend fun getMovieDetails(movieId: Int): IOResult<MovieDetails, MovieError> {
        return remoteDataSource.requestMovieDetails(movieId)
    }

    override suspend fun getMovieCredits(movieId: Int): IOResult<Credits, MovieError> {
        return remoteDataSource.requestMovieCredits(movieId)
    }
}
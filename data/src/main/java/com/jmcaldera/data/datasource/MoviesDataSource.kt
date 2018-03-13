package com.jmcaldera.data.datasource

import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.*

/**
 * Created by jmcaldera on 08-03-18.
 */
interface MoviesDataSource {

    suspend fun requestNowPlayingMovies(): IOResult<NowPlayingUpcoming, MovieError>

    suspend fun requestTopRatedMovies(): IOResult<TopRatedPopular, MovieError>

    suspend fun requestPopularMovies(): IOResult<TopRatedPopular, MovieError>

    suspend fun requestUpcomingMovies(): IOResult<NowPlayingUpcoming, MovieError>

    suspend fun requestMovieDetails(movieId: Int): IOResult<MovieDetails, MovieError>
}
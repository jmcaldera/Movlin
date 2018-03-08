package com.jmcaldera.domain.repository

import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.*

/**
 * Created by jmcaldera on 21-02-18.
 */
interface MoviesRepository {

    suspend fun getNowPlayingMovies(): IOResult<NowPlayingUpcoming, MovieError>

    suspend fun getTopRatedMovies(): IOResult<TopRatedPopular, MovieError>

    suspend fun getPopularMovies(): IOResult<TopRatedPopular, MovieError>

    suspend fun getUpcomingMovies(): IOResult<NowPlayingUpcoming, MovieError>
}
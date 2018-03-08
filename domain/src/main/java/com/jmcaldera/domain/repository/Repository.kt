package com.jmcaldera.domain.repository

import com.jmcaldera.domain.functional.IO
import com.jmcaldera.domain.functional.Result
import com.jmcaldera.domain.model.*

/**
 * Created by jmcaldera on 21-02-18.
 */
interface Repository {

    suspend fun getNowPlayingMovies(): IO<Result<NowPlaying, MovieError>>

    suspend fun getTopRatedMovies(): IO<Result<TopRated, MovieError>>

    suspend fun getPopularMovies(): IO<Result<Popular, MovieError>>

    suspend fun getUpcomingMovies(): IO<Result<Upcoming, MovieError>>
}
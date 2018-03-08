package com.jmcaldera.domain.repository

import com.jmcaldera.domain.functional.IO
import com.jmcaldera.domain.functional.Result
import com.jmcaldera.domain.model.*

/**
 * Created by jmcaldera on 21-02-18.
 */
interface Repository {

    fun getNowPlayingMovies(): IO<Result<NowPlaying, MovieError>>

    fun getTopRatedMovies(): IO<Result<TopRated, MovieError>>

    fun getPopularMovies(): IO<Result<Popular, MovieError>>

    fun getUpcomingMovies(): IO<Result<Upcoming, MovieError>>
}
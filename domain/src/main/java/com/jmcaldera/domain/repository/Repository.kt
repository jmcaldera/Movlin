package com.jmcaldera.domain.repository

import com.jmcaldera.domain.functional.IO
import com.jmcaldera.domain.functional.Result
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.model.NowPlaying

/**
 * Created by jmcaldera on 21-02-18.
 */
interface Repository {

    fun getNowPlayingMovies() : IO<Result<NowPlaying, MovieError>>

    fun getTopRatedMovies()

    fun getPopularMovies()

    fun getUpcomingMovies()
}
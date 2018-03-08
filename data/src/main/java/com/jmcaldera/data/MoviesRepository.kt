package com.jmcaldera.data

import com.jmcaldera.domain.functional.IO
import com.jmcaldera.domain.functional.Result
import com.jmcaldera.domain.model.*
import com.jmcaldera.domain.repository.Repository

/**
 * Created by jmcaldera on 21-02-18.
 */
class MoviesRepository : Repository {
    override suspend fun getNowPlayingMovies(): IO<Result<NowPlaying, MovieError>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getTopRatedMovies(): IO<Result<TopRated, MovieError>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getPopularMovies(): IO<Result<Popular, MovieError>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUpcomingMovies(): IO<Result<Upcoming, MovieError>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
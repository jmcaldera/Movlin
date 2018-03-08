package com.jmcaldera.data

import com.jmcaldera.domain.functional.IO
import com.jmcaldera.domain.functional.Result
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.model.NowPlaying
import com.jmcaldera.domain.repository.Repository

/**
 * Created by jmcaldera on 21-02-18.
 */
class MoviesRepository: Repository {
    override fun getNowPlayingMovies(): IO<Result<NowPlaying, MovieError>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTopRatedMovies() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPopularMovies() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUpcomingMovies() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
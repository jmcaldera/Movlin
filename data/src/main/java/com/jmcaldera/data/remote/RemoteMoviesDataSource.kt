package com.jmcaldera.data.remote

import com.jmcaldera.data.datasource.MoviesDataSource
import com.jmcaldera.data.extension.orElse
import com.jmcaldera.data.extension.transformResult
import com.jmcaldera.data.mapper.convertToDomain
import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.*

/**
 * Created by jmcaldera on 08-03-18.
 */
class RemoteMoviesDataSource(private val service: TmdbService) : MoviesDataSource {

    override suspend fun requestNowPlayingMovies(): IOResult<NowPlayingUpcoming, MovieError> {
        return service.getNowPlayingMovies().transformResult {
            convertToDomain(this)
        }.orElse { NotFoundError() }
    }

    override suspend fun requestTopRatedMovies(): IOResult<TopRatedPopular, MovieError> {
        return service.getTopRatedMovies().transformResult {
            convertToDomain(this)
        }.orElse { NotFoundError() }
    }

    override suspend fun requestPopularMovies(): IOResult<TopRatedPopular, MovieError> {
        return service.getPopularMovies().transformResult {
            convertToDomain(this)
        }.orElse { NotFoundError() }
    }

    override suspend fun requestUpcomingMovies(): IOResult<NowPlayingUpcoming, MovieError> {
        return service.getUpcomingMovies().transformResult {
            convertToDomain(this)
        }.orElse { NotFoundError() }
    }
}
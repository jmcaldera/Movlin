package com.jmcaldera.domain.usecase

import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.model.NowPlayingUpcoming
import com.jmcaldera.domain.repository.MoviesRepository

/**
 * Created by jmcaldera on 08-03-18.
 */
class GetUpcomingMoviesUseCase(private val moviesRepository: MoviesRepository):
        UseCase<IOResult<NowPlayingUpcoming, MovieError>, UseCase.None>() {

    override suspend fun execute(params: None): IOResult<NowPlayingUpcoming, MovieError> =
            moviesRepository.getUpcomingMovies()
}
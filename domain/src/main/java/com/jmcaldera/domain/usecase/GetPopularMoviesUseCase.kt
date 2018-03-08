package com.jmcaldera.domain.usecase

import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.model.TopRatedPopular
import com.jmcaldera.domain.repository.MoviesRepository

/**
 * Created by jmcaldera on 08-03-18.
 */
class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepository):
        UseCase<IOResult<TopRatedPopular, MovieError>, UseCase.None>() {

    override suspend fun execute(params: None?): IOResult<TopRatedPopular, MovieError> =
            moviesRepository.getPopularMovies()
}
package com.jmcaldera.domain.usecase

import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.Movie
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.repository.MoviesRepository

/**
 * Created by jmcaldera on 08-03-18.
 */
class GetTopRatedMoviesUseCase(private val moviesRepository: MoviesRepository) :
        UseCase<IOResult<List<Movie>, MovieError>, UseCase.None>() {

    override suspend fun execute(params: None?): IOResult<List<Movie>, MovieError> =
            moviesRepository.getTopRatedMovies()
}
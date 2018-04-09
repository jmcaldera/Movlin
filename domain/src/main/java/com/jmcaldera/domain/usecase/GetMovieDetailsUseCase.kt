package com.jmcaldera.domain.usecase

import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.Movie
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.repository.MoviesRepository

/**
 * Created by jmcaldera on 13-03-18.
 */
class GetMovieDetailsUseCase(private val moviesRepository: MoviesRepository):
        UseCase<IOResult<Movie, MovieError>, GetMovieDetailsUseCase.Params>() {

    override suspend fun execute(params: Params?): IOResult<Movie, MovieError> =
            moviesRepository.getMovieDetails(params?.movieId!!)

    data class Params(val movieId : Int)
}
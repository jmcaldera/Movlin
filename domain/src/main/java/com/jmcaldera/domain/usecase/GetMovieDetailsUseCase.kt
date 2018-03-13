package com.jmcaldera.domain.usecase

import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.MovieDetails
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.repository.MoviesRepository

/**
 * Created by jmcaldera on 13-03-18.
 */
class GetMovieDetailsUseCase(private val moviesRepository: MoviesRepository):
        UseCase<IOResult<MovieDetails, MovieError>, GetMovieDetailsUseCase.Params>() {

    override suspend fun execute(params: Params?): IOResult<MovieDetails, MovieError> =
            moviesRepository.getMovieDetails(params?.movieId!!)

    data class Params(val movieId : Int)
}
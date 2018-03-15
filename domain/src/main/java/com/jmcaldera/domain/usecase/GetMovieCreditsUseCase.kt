package com.jmcaldera.domain.usecase

import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.Credits
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.repository.MoviesRepository

/**
 * Created by jmcaldera on 15-03-18.
 */
class GetMovieCreditsUseCase(private val moviesRepository: MoviesRepository):
        UseCase<IOResult<Credits, MovieError>, GetMovieCreditsUseCase.Params>() {

    override suspend fun execute(params: Params?): IOResult<Credits, MovieError> =
            moviesRepository.getMovieCredits(params?.movieId!!)

    data class Params(val movieId : Int)
}
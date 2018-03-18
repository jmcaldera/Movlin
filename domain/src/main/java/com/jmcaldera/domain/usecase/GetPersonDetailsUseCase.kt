package com.jmcaldera.domain.usecase

import com.jmcaldera.domain.functional.IOResult
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.model.Person
import com.jmcaldera.domain.repository.MoviesRepository

/**
 * Created by jmcaldera on 18-03-18.
 */
class GetPersonDetailsUseCase(private val moviesRepository: MoviesRepository) :
        UseCase<IOResult<Person, MovieError>, GetPersonDetailsUseCase.Params>() {

    override suspend fun execute(params: Params?): IOResult<Person, MovieError> =
            moviesRepository.getPersonDetails(params?.personId!!)

    data class Params(val personId: Int)
}
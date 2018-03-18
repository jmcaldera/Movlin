package com.jmcaldera.movlin.people

import com.jmcaldera.domain.functional.asyncAwait
import com.jmcaldera.domain.functional.fold
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.model.NotFoundError
import com.jmcaldera.domain.model.UnauthorizedError
import com.jmcaldera.domain.usecase.GetPersonDetailsUseCase
import com.jmcaldera.movlin.model.mapper.convertPersonFromDomain

/**
 * Created by jmcaldera on 18-03-18.
 */
class PeoplePresenter(private val getPersonDetailsUseCase: GetPersonDetailsUseCase) :
        PeopleContract.Presenter {

    override lateinit var view: PeopleContract.View

    override suspend fun loadPersonDetails(id: Int) {
        if (view.isActive()) view.showLoading()

        val personResult = getPersonDetailsUseCase.execute(GetPersonDetailsUseCase.Params(id))

        personResult.asyncAwait { result ->
            result.fold(
                    onSuccess = {
                        if (view.isActive()) view.showDetails(convertPersonFromDomain(it))
                    },
                    onError = { movieError -> handleError(movieError) }
            )
        }
        if (view.isActive()) {
            view.hideLoading()
        }
    }

    private fun handleError(movieError: MovieError) {
        if (view.isActive()) {
            when (movieError) {
                is UnauthorizedError -> view.showUnauthorizedError()
                is NotFoundError -> view.showNotFoundError()
            }
        }
    }
}
package com.jmcaldera.movlin.details

import com.jmcaldera.domain.functional.asyncAwait
import com.jmcaldera.domain.functional.fold
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.model.NotFoundError
import com.jmcaldera.domain.model.UnauthorizedError
import com.jmcaldera.domain.usecase.GetMovieCreditsUseCase
import com.jmcaldera.domain.usecase.GetMovieDetailsUseCase
import com.jmcaldera.movlin.model.mapper.convertCreditsFromDomain
import com.jmcaldera.movlin.model.mapper.convertMovieDetailsFromDomain

/**
 * Created by jmcaldera on 13-03-18.
 */
class MovieDetailsPresenter(private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
                            private val getMovieCreditsUseCase: GetMovieCreditsUseCase) :
        MovieDetailsContract.Presenter {

    override lateinit var view: MovieDetailsContract.View

    override suspend fun loadMovieDetails(id: Int) {
        if (view.isActive()) {
            view.showLoading()
        }

        val movieResult = getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(id))
        val creditsResult = getMovieCreditsUseCase.execute(GetMovieCreditsUseCase.Params(id))

        movieResult.asyncAwait { result ->
            result.fold(
                    onSuccess = { movie ->
                        if (view.isActive()) {
                            view.showDetails(convertMovieDetailsFromDomain(movie))
                        }
                    },
                    onError = { movieError -> handleError(movieError) }
            )
        }

        creditsResult.asyncAwait { result ->
            result.fold(
                    onSuccess = { credits ->
                        if (view.isActive()) {
                            view.showCast(convertCreditsFromDomain(credits).cast)
                        }
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
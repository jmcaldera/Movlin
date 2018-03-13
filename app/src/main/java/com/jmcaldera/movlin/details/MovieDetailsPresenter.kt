package com.jmcaldera.movlin.details

import com.jmcaldera.domain.functional.asyncAwait
import com.jmcaldera.domain.functional.fold
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.model.NotFoundError
import com.jmcaldera.domain.model.UnauthorizedError
import com.jmcaldera.domain.usecase.GetMovieDetailsUseCase
import com.jmcaldera.movlin.model.MovieDetailsViewModel

/**
 * Created by jmcaldera on 13-03-18.
 */
class MovieDetailsPresenter(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) :
        MovieDetailsContract.Presenter {

    override lateinit var view: MovieDetailsContract.View

    override suspend fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun loadMovieDetails(id: Int) {
        if (view.isActive()) {
            view.showLoading()
        }

        val movieResult = getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(id))

        movieResult.asyncAwait { result ->
            result.fold(
                    onSuccess = { movie ->
                        if (view.isActive()) {
                            view.showDetails(MovieDetailsViewModel(movie.id, movie.title, movie.posterPath))
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
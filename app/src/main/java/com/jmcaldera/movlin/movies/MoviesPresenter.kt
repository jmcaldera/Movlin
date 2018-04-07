package com.jmcaldera.movlin.movies

import com.jmcaldera.domain.functional.asyncAwait
import com.jmcaldera.domain.functional.fold
import com.jmcaldera.domain.model.MovieError
import com.jmcaldera.domain.model.NotFoundError
import com.jmcaldera.domain.model.UnauthorizedError
import com.jmcaldera.domain.usecase.GetNowPlayingMoviesUseCase
import com.jmcaldera.domain.usecase.GetPopularMoviesUseCase
import com.jmcaldera.domain.usecase.GetTopRatedMoviesUseCase
import com.jmcaldera.domain.usecase.GetUpcomingMoviesUseCase
import com.jmcaldera.movlin.model.MovieViewModel

/**
 * Created by jmcaldera on 13-03-18.
 */
class MoviesPresenter(private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
                      private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
                      private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
                      private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase) : MoviesContract.Presenter {

    override lateinit var view: MoviesContract.View

    override suspend fun start() {
        if (view.isActive()) {
            view.showLoading()
        }
        val nowPlayingResult = getNowPlayingMoviesUseCase.execute()
        val popularResult = getPopularMoviesUseCase.execute()
        val topRatedResult = getTopRatedMoviesUseCase.execute()
        val upcomingResult = getUpcomingMoviesUseCase.execute()

        nowPlayingResult.asyncAwait { result ->
            result.fold(
                    onSuccess = { movies ->
                        if (view.isActive()) {
                            view.showNowPlayingMovies(movies.map {
                                MovieViewModel(it.id, it.title, it.posterPath)
                            })
                        }
                    },
                    onError = { movieError -> handleError(movieError) }
            )
        }

        popularResult.asyncAwait { result ->
            result.fold(
                    onSuccess = { popularList ->
                        if (view.isActive()) {
                            view.showPopularMovies(popularList.movies.map {
                                MovieViewModel(it.id, it.title, it.posterPath)
                            })
                        }
                    },
                    onError = { movieError -> handleError(movieError) }
            )
        }

        topRatedResult.asyncAwait { result ->
            result.fold(
                    onSuccess = { topRatedList ->
                        if (view.isActive()) {
                            view.showTopRatedMovies(topRatedList.movies.map {
                                MovieViewModel(it.id, it.title, it.posterPath)
                            })
                        }
                    },
                    onError = { movieError -> handleError(movieError) }
            )
        }

        upcomingResult.asyncAwait { result ->
            result.fold(
                    onSuccess = { movies ->
                        if (view.isActive()) {
                            view.showUpcomingMovies(movies.map {
                                MovieViewModel(it.id, it.title, it.posterPath)
                            })
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

    override fun onMovieClick(movie: MovieViewModel) {
        if (view.isActive()) view.navigateToMovieDetails(movie.id)
    }
}